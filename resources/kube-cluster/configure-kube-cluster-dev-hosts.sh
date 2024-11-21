#!/bin/bash

STARTING_IP=$1
CLUSTER_DOMAIN_PREFIX=$2
# forza la conversione a integer 
CLUSTER_CONTROL_PLANE_NODES=$(($3 + 0))
CLUSTER_WORKER_NODES=$(($4 + 0))
# esclude quanto c'è dopo l'ultimo punto 
MY_SUBNET=${STARTING_IP%.*}

HOSTS_FILE=/etc/hosts 

# aggiunge un # all'inizio delle linee che iniziano con 127.0.0.1 e 127.0.1.1 
function createModifiedEtcHosts
{
	echo "modifying 127.0.x.x entries in /etc/hosts"
	# Legge il file $INFILE e lo copia in $OUTFILE, ma: 
	# - aggiunge un # all'inizio delle linee che iniziano con 127.0. 
	sed s/^'127.0.'/'# 127.0.'/ ${HOSTS_FILE} > ${HOSTS_FILE}.new
	# aggiunge di nuovo 127.0.0.1 localhost
    echo "127.0.0.1 localhost" >> ${HOSTS_FILE}.new 
	mv ${HOSTS_FILE} ${HOSTS_FILE}.bak
	mv ${HOSTS_FILE}.new ${HOSTS_FILE}
}

# aggiunge a /etc/hosts le seguenti entry 
# - "10.11.1.71 kube-1 kube-cluster kube-control-plane kube-nfs"
# - "10.11.1.72 kube-2 kube-cluster kube-node"
# - "10.11.1.73 kube-3 kube-cluster kube-node"
#
# in teoria, kube-cluster dovrebbe essere servito da un DNS, 
# a rotazione su uno qualunque di questi nodi
#
function configureKubeClusterClientEtcHosts {
	echo "adding entries for kubernetes dev client node to /etc/hosts"
	echo "adding entries for kube-cluster nodes to /etc/hosts"
	read IP_A IP_B IP_C IP_D <<<"${STARTING_IP//./ }"
	IP_PREFIX=${IP_A}.${IP_B}.${IP_C}.
	IP_STARTING_NUM=${IP_D}

	# prima i nodi master 
	for ((i = 1; i <= ${CLUSTER_CONTROL_PLANE_NODES}; i++))
	do 
		CURRENT_NUM=$(($IP_STARTING_NUM+$i-1))
		CURRENT_IP=${IP_PREFIX}${CURRENT_NUM}
		CURRENT_NODE=${CLUSTER_DOMAIN_PREFIX}$i
		CURRENT_DOMAIN=${CLUSTER_DOMAIN_PREFIX}cluster
		CURRENT_GROUP=${CLUSTER_DOMAIN_PREFIX}control-plane
		CURRENT_NFS=${CLUSTER_DOMAIN_PREFIX}nfs
		echo "${CURRENT_IP} ${CURRENT_NODE} ${CURRENT_DOMAIN} ${CURRENT_GROUP} ${CURRENT_NFS}" >> ${HOSTS_FILE}
	done
	# poi i nodi worker 
	for ((i = ${CLUSTER_CONTROL_PLANE_NODES}+1; i <= ${CLUSTER_CONTROL_PLANE_NODES}+${CLUSTER_WORKER_NODES}; i++))
	do 
		CURRENT_NUM=$(($IP_STARTING_NUM+$i-1))
		CURRENT_IP=${IP_PREFIX}${CURRENT_NUM}
		CURRENT_NODE=${CLUSTER_DOMAIN_PREFIX}$i
		CURRENT_DOMAIN=${CLUSTER_DOMAIN_PREFIX}cluster
		CURRENT_GROUP=${CLUSTER_DOMAIN_PREFIX}node
		echo "${CURRENT_IP} ${CURRENT_NODE} ${CURRENT_DOMAIN} ${CURRENT_GROUP}" >> ${HOSTS_FILE}
	done
}

#function configureKubeClusterClientEtcHosts {
#	echo "adding entries for kube-cluster nodes to /etc/hosts"
#	echo "10.11.1.71 kube-1 kube-cluster kube-control-plane kube-nfs" >> ${HOSTS_FILE}
#	echo "10.11.1.72 kube-2 kube-cluster kube-node" >> ${HOSTS_FILE}
#	echo "10.11.1.73 kube-3 kube-cluster kube-node" >> ${HOSTS_FILE}
#}

# aggiunge a /etc/hosts le seguenti entry 
# - "10.11.1.131 kube-dev"
function configureKubeClusterDevEtcHosts {
	# calcola il mio indirizzo IP (sulla rete 10.11.1.xx)
	# ubuntu 20.04 e 22.04
	MY_IP_ADDR=$(ip address | grep ${MY_SUBNET}. | awk '{ print $2 }' | cut -d/ -f1)
	echo "adding entries for kube-dev to /etc/hosts on " ${MY_IP_ADDR}
	echo ${MY_IP_ADDR} " kube-dev" >> ${HOSTS_FILE}
}

echo "setup /etc/hosts on a kube-cluster client node"
createModifiedEtcHosts
configureKubeClusterClientEtcHosts
configureKubeClusterDevEtcHosts