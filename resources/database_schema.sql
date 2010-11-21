CREATE TABLE `game` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `uuid` varchar(40) NOT NULL default '',
  `user_id` int(11) default NULL,
  `createdOn` datetime default NULL,
  `status` varchar(15) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

CREATE TABLE `move` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `game_id` int(11) NOT NULL,
  `x` tinyint(4) unsigned default '0',
  `y` tinyint(4) unsigned default '0',
  `player` varchar(10) NOT NULL default 'Player',
  `createdOn` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=355 DEFAULT CHARSET=utf8;

CREATE TABLE `ranking` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `wins` int(11) NOT NULL,
  `losses` int(11) NOT NULL,
  `draws` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `score` decimal(10,0) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) default NULL,
  `password` varchar(40) default NULL,
  `createdOn` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
