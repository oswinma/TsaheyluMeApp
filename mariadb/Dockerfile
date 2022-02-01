FROM mariadb:10.6.5
copy  /init/ /docker-entrypoint-initdb.d/
copy  /sql/ /opt/sql/
#ADD mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf