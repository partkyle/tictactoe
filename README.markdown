Tic Tac Toe
===========

This is an implementation (front-end and backend) of the game of Tic Tac Toe.

It is written in Java using the [play! framework](http://www.playframework.org/) using [jQuery](http://www.jquery.org) for the front end javascript. Some styling is done using [Blueprint CSS](http://blueprintcss.org).

Technologies used in the application:

- [Gravatar](http://gravatar.com/) for the user profile pictures.
- [Google Charts API](http://chart.apis.google.com) for generating graphs.
- [Google fonts API](http://fonts.googleapis.com/) to enable Droid Sans.

The AI implementation is relatively simple. It follows these 3 simple rules:

1. If there is a winning move, take that square.
1. if there is a winning move that can be made by that player, block that square.
1. If none of the other moves are available, move randomly.
