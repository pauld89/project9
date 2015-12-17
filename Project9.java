//Dairo Paulino
//Project 9


float horizon;
float surface;
paul dairo;
paul[] group;
int many=4;
boolean action=true;

//// SETUP:  screen size.
void setup() {
  size( 600, 400 );
  surface=  height/4;
  dairo=  new paul( );
  dairo.r=  255;
  dairo.g=  255;
  dairo.b=  200;
  dairo.legs=  5;
  dairo.name=  "dairo";
  //
  dairo.y=  height-50;
  dairo.dy=0;
  group=  new paul[many];
  reset();
}
void reset() {    
  dairo.x=0;
  action=  ! action;
  float spacing=  width / (many+1);
  float paulX= spacing;
  //INIT  
  for( int i=0; i<many; i++) {
    group[i]=  new paul(  i, paulX );
    paulX += spacing;
  }
}

//// NEXT FRAME:  scene, show, action
void draw() {
  //// SCENE:  sky, sun, tree, house, etc.
  background( 0,78,242 );                // sky
  fill( 247,124,0 );
  ellipse( width*3/4, height/10, 100,100 );    // sun
  // Grass
  fill( 100,200,100 );
  rect( 1,horizon, width,height*3/4 );      // grass.
  dairo.show();
  dairo.move();
   if (key >= 'A' && key <= 'Z') {
    paulReport( surface+50, group, group.length);
  }
  else action();
  text( "Arrays of objects.  \n(dairo.)", width/3, 20 );
  
}
void keyPressed() {
  if (key == 'q') exit();
  if (key == 'r') reset();
}
void action() {   
  if (! action) return;
  for( int i=0; i<many; i++) {
    group[i].show();
  }
  for( int i=0; i<many; i++) {
    group[i].move();
  }
  for (int i=0; i<many; i++) {
    for (int j=i+1; j<many; j++) {
      if (group[j].hit( group[i].x, group[i].y )) {
        group[i].y=surface;
      }
    }
  } 
  for (int i=0; i<many; i++) {
  if (dairo.hit( group[i].x, group[i].y )) {
  group[i].legs--;
        group[i].y=  surface;
        dairo.legs++;}}
}
void paulReport( float top, paul[] a, int many ) {
  fill(255,0,100);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  fill(150,0,0);
  text( "#", x+20, y );
  fill(0);
  for (int i=0; i<many; i++) {
    y += 15; 
  }
}
class paul {
  float x=200, y=100, dx=4, dy=2;
  float w=60,h=30;
  float r=255, g=50, b=60;
  int legs=1;
  int num;
  String name="";
  paul( int n, float x ) {
    num=  n;
    this.x=  x;
    y=  random( surface,height );
    legs=  int( random( 2, 9 ) );
  }
  paul( ) { }
  void show() {
    fill( r, g, b );
    ellipse( x,y, w,h );
    float xx= x-w/2;
    for (int j=0; j<legs; j++) {
      triangle( xx, y-h/4, xx+20, y-10-h/2, xx+40, y-h/2 );
       triangle( xx, y-h/4, xx+40, y-10-h/2, xx+40, y-h/2 );
      xx += 10;
    }
    fill(0);
    text( num, x+w/8,y );
    text( name, x-w/6,y+20 );
    if (legs>1) text( legs, x-w/8,y );
  }
  void move() {
    fill(0);
    x += dx;
    y += dy;
    if (x>width) x=0;
  }boolean hit( float xx, float yy ) {
if (dist(xx,yy, this.x,this.y) < w) return true;
else return false;
  }
}
