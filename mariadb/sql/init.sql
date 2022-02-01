CREATE DATABASE IF NOT EXISTS `tsaheylu_me` /*!40100 DEFAULT CHARACTER SET utf8 */;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on tsaheylu_me.* to 'springuser'@'%';
flush privileges;
use tsaheylu_me;



