# Custom View Samples On Android

I love **Custom Views** on Android because they provide the power to create anything you think of, understanding the fundamentals of custom
views automatically simplifies the replication of any design out there. Well, no one can *stop* learning, so as I go on, and come across
challenging views, I replicate them, add a screenshot of the built view, a link to the code, and a walkthrough on how it was built.
Check them out!

## ChartView

  Actually I came across this during a talk about custom views, and I wanted to replicate it, it went fine, and this was the layout with
variable data.

<img src="views/chart_view_5.png" width="250"/> <img src="views/chart_view_10.png" width="250"/>
  
  The image to the left has 5 bars while the one to the right has 10?. Well the way I started off was to draw the main and cross axes, 
I created a paint object and drew the x and y axes with the appropriate colors, after that I divided the height of the screen by 10, so
as to know the exact spacing needed between the graph lines. Then I divided the width of the screen minus the padding, the width of the
left axis, and the default spacing by the amount of bars to be drawn. I then calculate by the percent of the bar, the height of the bar is
deduced. You can go through the code <a href="">here</a>
