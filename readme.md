Installation of mysql image with initial database for linux.

1.docker pull mysql/mysql-server:tag    (where tag is version of mysql server)
2.docker run --name=mysql1 -d mysql/mysql-server:tag  (crate mysql docker image)
3.docker run --name=wir -d mysql/mysql-server:5.7  (creates docker image)
### Usefull command ### docker ps -a   (check all installed images on docker)
4.docker logs wir (find initial root password)
5.docker exec -it wir mysql -uroot -p   (run it then paste initial password string from previous command)
6. ALTER USER 'root'@'localhost' IDENTIFIED BY 'wir';  (you need to use ; at the end, and this command is changing initial password)
7. CREATE USER 'wir'@'%' IDENTIFIED BY 'wir';
8. GRANT ALL PRIVILEGES ON *.* to 'wir'@'%'; 
9. CRATE DATABASE wir;