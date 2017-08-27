package lab4ap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class lab4 {  //WORLD CLASS
private static int time=0;
private static PriorityQueue<Animal> q;
private static int ftime;
static abstract class Animal{
	int time;
	int health;
	int x;
	int y;
	int no;
	String type;
	Animal(int t,int xcor,int ycor,String typ,int h,int n){
		time=t;
		health=h;
		x=xcor;
		y=ycor;
		type=typ;
		no=n;
	}
	abstract void move();
}
static class herbivore extends Animal{
int gc;
int turns=0;
	herbivore(int t, int xcor, int ycor, String typ,int health,int grass,int no) {
		super(t, xcor, ycor, typ,health, no);
		gc=grass;
	}
	
	void move(){
		
	}
}
static class carnivore extends Animal{
int turnsofc=0;
	carnivore(int t, int xcor, int ycor, String typ,int health,int no) {
		super(t, xcor, ycor, typ,health,no);
	}
	void move(){
		
	}
}
static class cmp implements Comparator<Animal>{
	@Override
	public int compare(Animal a,Animal b){
		int timea=a.time;int timeb=b.time;
		if(timea>time){
			time=timea;
		}
		if(timeb>time){
			time=timeb;
		}
		if (a.time!=b.time){
		if (timea>timeb)
			return 1;
		else if (timeb>timea)
			return -1;
		}
		else{
		if(a.health!=b.health){
			if (a.health>b.health)
				return -1;
			if (b.health>a.health)
				return 1;
			
		}
		else{
			if (!a.type.equals(b.type)){
				if (a.type.equals("h"))
					return -1;
				return 1;
			}
			else{
			int dista=dist(a.x,a.y,0,0);
			int distb=dist(b.x,b.y,0,0);
			if (dista>distb)
				return 1;
			else if (distb>dista)
				return -1;
			}

		}
		}
		return 0;
	}
}

public static  int dist(int x1,int y1,float x_g1,float y_g1){
	return (int) Math.round(Math.sqrt(Math.pow((float) y_g1-(float)y1,2)+ Math.pow((float) x_g1-(float)x1,2)));
}
public static  boolean prob(int percent){
	Random r = new Random();
	boolean torf = r.nextInt(100) < percent;
	return torf;
}
public static int travx(int x1,float x_g2,int step,int dist){
	int fin=(int) (x1+(x_g2-x1)*(step)/dist);
	return fin;
}
public static int travy(int y1,float y_g2,int step,int dist){
	int fin=(int) (y1+(y_g2-y1)*(step)/dist);
	return fin;
}
public static  int circle(float x0,float y0,int x1,int y1,float r){
	int func=(int) ((int) Math.pow((x1-x0),2)+Math.pow(y1-y0, 2));
	if(func<=r){
		return 1;
	}
	else{
		return -1;
	}
}
public static void main(String args[]){
	Scanner s=new Scanner(System.in);
	ftime=s.nextInt();
	
	float x_g1,y_g1;
	float r_g1,avail_g1;
	x_g1=s.nextInt();y_g1=s.nextInt();r_g1=s.nextFloat();avail_g1=s.nextFloat();
	float x_g2,y_g2;
	float r_g2,avail_g2;
	x_g2=s.nextInt();y_g2=s.nextInt();r_g2=s.nextFloat();avail_g2=s.nextFloat();
	int h_herbi,gc_herbi;
	h_herbi=s.nextInt();gc_herbi=s.nextInt();
	int x_fherbi,y_fherbi,t_fherbi;
	x_fherbi=s.nextInt();
	y_fherbi=s.nextInt();
	t_fherbi=s.nextInt();
	herbivore fh=new herbivore(t_fherbi, x_fherbi, y_fherbi, "h", h_herbi, gc_herbi,1);
	int x_sherbi,y_sherbi,t_sherbi;
	x_sherbi=s.nextInt();
	y_sherbi=s.nextInt();
	t_sherbi=s.nextInt();
	herbivore sh=new herbivore(t_sherbi, x_sherbi, y_sherbi, "h", h_herbi, gc_herbi,2);
	int h_carni=s.nextInt();
	int x_fcarni,y_fcarni,t_fcarni;
	x_fcarni=s.nextInt();
	y_fcarni=s.nextInt();
	t_fcarni=s.nextInt();
	carnivore fc=new carnivore(t_fcarni, x_fcarni, y_fcarni, "c", h_carni,1);
	int x_scarni,y_scarni,t_scarni;
	x_scarni=s.nextInt();
	y_scarni=s.nextInt();
	t_scarni=s.nextInt();
	carnivore sc=new carnivore(t_scarni, x_scarni, y_scarni, "c", h_carni,2);

	Comparator<Animal> comparator = new cmp();
	q=new PriorityQueue<>(4,comparator);
	System.out.println("The Simulation Begins -");
	q.add(fh);
	q.add(sh);
	q.add(fc);
	q.add(sc);
	int turn=0;
	ArrayList<Integer> hcount=new ArrayList<Integer>();
	hcount.add(1);
	hcount.add(2);
	ArrayList<Integer> ccount=new ArrayList<Integer>();
	hcount.add(1);
	hcount.add(2);
	while(q.size()>0 && ftime>(turn) && time<ftime-1){

	Animal deq1=q.poll();
	if( deq1.type=="h"){
		herbivore deq=(herbivore) deq1;
		if(deq.no==1){
		System.out.println("It is First herbivore");}
		else{
			System.out.println("It is Second herbivore");
		}
		if (ccount.size()<=0){
			//no carni
			boolean verdict=prob(50);
			if(verdict){
				;
			}
			else{
				if(circle(x_g1, y_g1,deq.x ,deq.y ,r_g1 )==1){
					int d=dist(deq.x,deq.y,x_g2,y_g2);
					deq.x=travx(deq.x, x_g2, 5, d);
					
					deq.y=travy(deq.y, y_g2, 5, d);
					if(deq.no==1){
						x_fherbi=deq.x;
						y_fherbi=deq.y;
					}
					else{
						x_sherbi=deq.x;
						y_sherbi=deq.y;
					}
					deq.health-=25;
					
				}
				else if(circle(x_g2, y_g2,deq.x ,deq.y ,r_g2 )==1){
					int d=dist(deq.x,deq.y,x_g1,y_g1);
					deq.x=travx(deq.x, x_g1, 5, d);
					deq.y=travy(deq.y, y_g1, 5, d);
					if(deq.no==1){
						x_fherbi=deq.x;
						y_fherbi=deq.y;
					}
					else{
						x_sherbi=deq.x;
						y_sherbi=deq.y;
					}
					deq.health-=25;
				}
				else{
					if(dist(deq.x, deq.y, x_g1, y_g1)>=dist(deq.x, deq.y, x_g2, y_g2)){
						int d=dist(deq.x,deq.y,x_g2,y_g2);
						deq.x=travx(deq.x, x_g2, 5, d);
						deq.y=travy(deq.y, y_g2, 5, d);
						if(deq.no==1){
							x_fherbi=deq.x;
							y_fherbi=deq.y;
						}
						else{
							x_sherbi=deq.x;
							y_sherbi=deq.y;
						}
						
					}
					else{
						int d=dist(deq.x,deq.y,x_g1,y_g1);
						deq.x=travx(deq.x, x_g1, 5, d);
						deq.y=travy(deq.y, y_g1, 5, d);
						if(deq.no==1){
							x_fherbi=deq.x;
							y_fherbi=deq.y;
						}
						else{
							x_sherbi=deq.x;
							y_sherbi=deq.y;
						}
						
					}
				}
			}
		}
		else{
			//atleast one carni
			if(circle(x_g2, y_g2,deq.x ,deq.y ,r_g2 )==1 ||circle(x_g1, y_g1,deq.x ,deq.y ,r_g1 )==1){
				//herbi inside grassland
				deq.turns=0;
				if(circle(x_g2, y_g2,deq.x ,deq.y ,r_g2 )==1){
					//herbi in 2nd grassland
					if(avail_g2>=deq.gc){
						boolean verdict=prob(90);
						if(verdict){
							avail_g2-=deq.gc;
							deq.health+=(0.50*deq.health);
						}
						else{
							verdict=prob(50);
							if(verdict){
								//move away from carni
								if (ccount.size()==1){
									if(ccount.get(0)==1){
										if(deq.x==x_fcarni && deq.y==y_fcarni){
											int d=dist(deq.x,deq.y,x_g2,y_g2);
											deq.x=travx(deq.x, x_g2, 4, d);
											deq.y=travy(deq.y, y_g2, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
										else{
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -4, d);
										deq.y=travy(deq.y, y_fcarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
									else{
										if(deq.x==x_scarni && deq.y==y_scarni){
											int d=dist(deq.x,deq.y,x_g2,y_g2);
											deq.x=travx(deq.x, x_g2, 4, d);
											deq.y=travy(deq.y, y_g2, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -4, d);
										deq.y=travy(deq.y, y_scarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
								}
								else{
									//both carni alive
									int d1=dist(deq.x,deq.y,x_fcarni,y_fcarni);
									int d2=dist(deq.x,deq.y,x_scarni,y_scarni);
									if(d1>=d2){
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -2, d);
										deq.y=travy(deq.y, y_fcarni, -2, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -2, d);
										deq.y=travy(deq.y, y_scarni, -2, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
								}
							}
							else{
								//move to next grassland 3 unit
								int d=dist(deq.x,deq.y,x_g1,y_g1);
								deq.x=travx(deq.x, x_g1, 3, d);
								deq.y=travy(deq.y, y_g1, 3, d);
								if(deq.no==1){
									x_fherbi=deq.x;
									y_fherbi=deq.y;
								}
								else{
									x_sherbi=deq.x;
									y_sherbi=deq.y;
								}
								deq.health-=25;
							}
						}
					}
					else{
						//grass in 2nd grassland less than herbi cap
						boolean verdict=prob(20);
						if(verdict){
							if(avail_g2>0){
							avail_g2=0;
							deq.health+=(0.20*deq.health);}

						}
						else{
							verdict=prob(70);
							if(verdict){
								//move 4 away from near carni 
								if (ccount.size()==1){
									if(ccount.get(0)==1){
										if(deq.x==x_fcarni && deq.y==y_fcarni){
											int d=dist(deq.x,deq.y,x_g2,y_g2);
											deq.x=travx(deq.x, x_g2, 4, d);
											deq.y=travy(deq.y, y_g2, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
										else{
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -4, d);
										deq.y=travy(deq.y, y_fcarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
									else{
										if(deq.x==x_scarni && deq.y==y_scarni){
											int d=dist(deq.x,deq.y,x_g2,y_g2);
											deq.x=travx(deq.x, x_g2, 4, d);
											deq.y=travy(deq.y, y_g2, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -4, d);
										deq.y=travy(deq.y, y_scarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
								}
								else{
									//both carni alive
									int d1=dist(deq.x,deq.y,x_fcarni,y_fcarni);
									int d2=dist(deq.x,deq.y,x_scarni,y_scarni);
									if(d1>=d2){
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -4, d);
										deq.y=travy(deq.y, y_fcarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -4, d);
										deq.y=travy(deq.y, y_scarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
								}
							}
							else{
								//move 2 towards near grass
								int d=dist(deq.x,deq.y,x_g1,y_g1);
								deq.x=travx(deq.x, x_g1, 2, d);
								deq.y=travy(deq.y, y_g1, 2, d);
								if(deq.no==1){
									x_fherbi=deq.x;
									y_fherbi=deq.y;
								}
								else{
									x_sherbi=deq.x;
									y_sherbi=deq.y;
								}
								deq.health-=25;
							}
						}
					}
				}
				else{
					//herbi in 1st grassland
					if(avail_g1>=deq.gc){
						boolean verdict=prob(90);
						if(verdict){
							avail_g1-=deq.gc;
							deq.health+=(0.50*deq.health);
						}
						else{
							verdict=prob(50);
							if(verdict){
								//move away from carni
								if (ccount.size()==1){
									if(ccount.get(0)==1){
										if(deq.x==x_fcarni && deq.y==y_fcarni){
											int d=dist(deq.x,deq.y,x_g1,y_g1);
											deq.x=travx(deq.x, x_g1, 4, d);
											deq.y=travy(deq.y, y_g1, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
										else{
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -4, d);
										deq.y=travy(deq.y, y_fcarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
									else{
										if(deq.x==x_scarni && deq.y==y_scarni){
											int d=dist(deq.x,deq.y,x_g1,y_g1);
											deq.x=travx(deq.x, x_g1, 4, d);
											deq.y=travy(deq.y, y_g1, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -4, d);
										deq.y=travy(deq.y, y_scarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
								}
								else{
									//both carni alive
									int d1=dist(deq.x,deq.y,x_fcarni,y_fcarni);
									int d2=dist(deq.x,deq.y,x_scarni,y_scarni);
									if(d1>=d2){
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -2, d);
										deq.y=travy(deq.y, y_fcarni, -2, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -2, d);
										deq.y=travy(deq.y, y_scarni, -2, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
								}
							}
							else{
								//move to next grassland 3 unit
								int d=dist(deq.x,deq.y,x_g2,y_g2);
								deq.x=travx(deq.x, x_g2, 3, d);
								deq.y=travy(deq.y, y_g2, 3, d);
								if(deq.no==1){
									x_fherbi=deq.x;
									y_fherbi=deq.y;
								}
								else{
									x_sherbi=deq.x;
									y_sherbi=deq.y;
								}
								deq.health-=25;
							}
						}
					}
					else{
						//grass in 1st grassland less than herbi cap
						boolean verdict=prob(20);
						if(verdict){
							if(avail_g1>0){
							avail_g1=0;
							deq.health+=(0.20*deq.health);}

						}
						else{
							verdict=prob(70);
							if(verdict){
								//move 4 away from near carni 
								if (ccount.size()==1){
									
									if(ccount.get(0)==1){
										if(deq.x==x_fcarni && deq.y==y_fcarni){
											int d=dist(deq.x,deq.y,x_g1,y_g1);
											deq.x=travx(deq.x, x_g1, 4, d);
											deq.y=travy(deq.y, y_g1, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
										else{
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -4, d);
										deq.y=travy(deq.y, y_fcarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
									else{
										if(deq.x==x_scarni && deq.y==y_scarni){
											int d=dist(deq.x,deq.y,x_g1,y_g1);
											deq.x=travx(deq.x, x_g1, 4, d);
											deq.y=travy(deq.y, y_g1, 4, d);
											if(deq.no==1){
												x_fherbi=deq.x;
												y_fherbi=deq.y;
											}
											else{
												x_sherbi=deq.x;
												y_sherbi=deq.y;
											}
											deq.health-=25;
										}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -4, d);
										deq.y=travy(deq.y, y_scarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;}
									}
								}
								else{
									//both carni alive
									int d1=dist(deq.x,deq.y,x_fcarni,y_fcarni);
									int d2=dist(deq.x,deq.y,x_scarni,y_scarni);
									if(d1>=d2){
										int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
										deq.x=travx(deq.x, x_fcarni, -4, d);
										deq.y=travy(deq.y, y_fcarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
									else{
										int d=dist(deq.x,deq.y,x_scarni,y_scarni);
										deq.x=travx(deq.x, x_scarni, -4, d);
										deq.y=travy(deq.y, y_scarni, -4, d);
										if(deq.no==1){
											x_fherbi=deq.x;
											y_fherbi=deq.y;
										}
										else{
											x_sherbi=deq.x;
											y_sherbi=deq.y;
										}
										deq.health-=25;
									}
								}
							}
							else{
								//move 2 towards near grass
								int d=dist(deq.x,deq.y,x_g2,y_g2);
								deq.x=travx(deq.x, x_g2, 2, d);
								deq.y=travy(deq.y, y_g2, 2, d);
								if(deq.no==1){
									x_fherbi=deq.x;
									y_fherbi=deq.y;
								}
								else{
									x_sherbi=deq.x;
									y_sherbi=deq.y;
								}
								deq.health-=25;
							}
						}
						
						
					}
				}
			}
			else{
				//not inside grassland
			deq.turns+=1;
			boolean verdict=prob(5);
			if(verdict){
				;
			}
			else{
				verdict=prob(65);
				if(verdict){
					if(dist(deq.x, deq.y, x_g1, y_g1)>=dist(deq.x, deq.y, x_g2, y_g2)){
						int d=dist(deq.x,deq.y,x_g2,y_g2);
						deq.x=travx(deq.x, x_g2, 5, d);
						deq.y=travy(deq.y, y_g2, 5, d);
						if(deq.no==1){
							x_fherbi=deq.x;
							y_fherbi=deq.y;
						}
						else{
							x_sherbi=deq.x;
							y_sherbi=deq.y;
						}
					}
					else{
						int d=dist(deq.x,deq.y,x_g1,y_g1);
						deq.x=travx(deq.x, x_g1, 5, d);
						deq.y=travy(deq.y, y_g1, 5, d);
						if(deq.no==1){
							x_fherbi=deq.x;
							y_fherbi=deq.y;
						}
						else{
							x_sherbi=deq.x;
							y_sherbi=deq.y;
						}
					}
				}
				else{
					if (ccount.size()==1){
						if(ccount.get(0)==1){
							if(deq.x==x_fcarni && deq.y==y_fcarni){
								int d=dist(deq.x,deq.y,x_g1,y_g1);
								deq.x=travx(deq.x, x_g1, 4, d);
								deq.y=travy(deq.y, y_g1, 4, d);
								if(deq.no==1){
									x_fherbi=deq.x;
									y_fherbi=deq.y;
								}
								else{
									x_sherbi=deq.x;
									y_sherbi=deq.y;
								}
								deq.health-=25;
							}
							else{
							int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
							deq.x=travx(deq.x, x_fcarni, -4, d);
							deq.y=travy(deq.y, y_fcarni, -4, d);
							if(deq.no==1){
								x_fherbi=deq.x;
								y_fherbi=deq.y;
							}
							else{
								x_sherbi=deq.x;
								y_sherbi=deq.y;
							}
							deq.health-=25;}
						}
						else{
							if(deq.x==x_scarni && deq.y==y_scarni){
								int d=dist(deq.x,deq.y,x_g1,y_g1);
								deq.x=travx(deq.x, x_g1, 4, d);
								deq.y=travy(deq.y, y_g1, 4, d);
								if(deq.no==1){
									x_fherbi=deq.x;
									y_fherbi=deq.y;
								}
								else{
									x_sherbi=deq.x;
									y_sherbi=deq.y;
								}
								deq.health-=25;
							}
						else{
							int d=dist(deq.x,deq.y,x_scarni,y_scarni);
							deq.x=travx(deq.x, x_scarni, -4, d);
							deq.y=travy(deq.y, y_scarni, -4, d);
							if(deq.no==1){
								x_fherbi=deq.x;
								y_fherbi=deq.y;
							}
							else{
								x_sherbi=deq.x;
								y_sherbi=deq.y;
							}
							deq.health-=25;}
						}
					}
					else{
						//both carni alive
						int d1=dist(deq.x,deq.y,x_fcarni,y_fcarni);
						int d2=dist(deq.x,deq.y,x_scarni,y_scarni);
						if(d1>=d2){
							int d=dist(deq.x,deq.y,x_fcarni,y_fcarni);
							deq.x=travx(deq.x, x_fcarni, -4, d);
							deq.y=travy(deq.y, y_fcarni, -4, d);
							if(deq.no==1){
								x_fherbi=deq.x;
								y_fherbi=deq.y;
							}
							else{
								x_sherbi=deq.x;
								y_sherbi=deq.y;
							}
						}
						else{
							int d=dist(deq.x,deq.y,x_scarni,y_scarni);
							deq.x=travx(deq.x, x_scarni, -4, d);
							deq.y=travy(deq.y, y_scarni, -4, d);
							if(deq.no==1){
								x_fherbi=deq.x;
								y_fherbi=deq.y;
							}
							else{
								x_sherbi=deq.x;
								y_sherbi=deq.y;
							}
						}
					}
				}
			}
			}
		}
	
	turn+=1;
	if(deq.turns>7){
		deq.health-=5;
	}
	System.out.println("Health after taking turn is "+deq.health);
	if(deq.health<=0){
		try{
		if(deq.no==1){
			System.out.println("First Herbivore dead");
			hcount.remove(0);
		}
		else{
			System.out.println("Second Herbivore dead");

			hcount.remove(1);
		}
		q.remove(deq);}
		catch (Exception e){
			;
		}
		
	}
	else{
		
	q.remove(deq);
	Random r = new Random();
	deq.time=(r.nextInt(ftime-1-time+1+1)+time+1);
	if(deq.time>=ftime-1){
		break;
	}
	q.add(deq);
	}
	}
	else{
		
		//its a carni !
		carnivore deq=(carnivore) deq1;
		if(deq.no==1){
			System.out.println("It is First carnivore");}
			else{
				System.out.println("It is Second carnivore");
			}
		if(hcount.size()<=0){
			;
		}
		else{
			//atleast one herbi
			boolean rad1=false;
			if(hcount.size()>1){
			int d1=dist(deq.x,deq.y,x_fherbi,y_fherbi);
			int d2=dist(deq.x,deq.y,x_sherbi,y_sherbi);
			if(d1>5 && d2>5){
				deq.turnsofc+=1;
			}
			else{
				deq.turnsofc=0;
			}
			if(d1<=1 && d2<=1){
				rad1=true;
			if(d1>=d2){
				
				deq.x=x_sherbi;
				deq.y=y_sherbi;
				if(deq.no==1){
					x_fcarni=deq.x;
					y_fcarni=deq.y;
				}
				else{
					x_scarni=deq.x;
					y_scarni=deq.y;
				}
				for(Animal a:q){
					if(a.type.equals("h") && a.no==2){
						
						deq.health+=((2/3)*a.health);
						q.remove(a);
					}
				}
				hcount.remove(1);
				
			}
			else{
			
				deq.x=x_fherbi;
				deq.y=y_fherbi;
				if(deq.no==1){
					x_fcarni=deq.x;
					y_fcarni=deq.y;
				}
				else{
					x_scarni=deq.x;
					y_scarni=deq.y;
				}
				for(Animal a:q){
					if(a.type.equals("h") && a.no==1){
						deq.health+=((2/3)*a.health);
						q.remove(a);
						
					}
				}
				hcount.remove(0);
				

			}
			}
			
			else{
				if(d1<=1){
					rad1=true;
					deq.x=x_fherbi;
					deq.y=y_fherbi;
					if(deq.no==1){
						x_fcarni=deq.x;
						y_fcarni=deq.y;
					}
					else{
						x_scarni=deq.x;
						y_scarni=deq.y;
					}
					for(Animal a:q){
						if(a.type.equals("h") && a.no==1){
							deq.health+=((2/3)*a.health);
							q.remove(a);
							
						}
					}
					hcount.remove(0);
					
				}
				else if(d2<=1){
					rad1=true;
					deq.x=x_sherbi;
					deq.y=y_sherbi;
					if(deq.no==1){
						x_fcarni=deq.x;
						y_fcarni=deq.y;
					}
					else{
						x_scarni=deq.x;
						y_scarni=deq.y;
					}
					for(Animal a:q){
						if(a.type.equals("h") && a.no==2){
							q.remove(a);
							deq.health+=((2/3)*a.health);
						}
					}
					hcount.remove(1);
					
				}
			}
			}
			else{
				//one herbi
				if(hcount.get(0)==1){
					int d1=dist(deq.x,deq.y,x_fherbi,y_fherbi);
					if(d1>5){
						deq.turnsofc+=1;
					}
					else{
						deq.turnsofc=0;
					}
					if(d1<=1){
						rad1=true;
						deq.x=x_fherbi;
						deq.y=y_fherbi;
						if(deq.no==1){
							x_fcarni=deq.x;
							y_fcarni=deq.y;
						}
						else{
							x_scarni=deq.x;
							y_scarni=deq.y;
						}
						for(Animal a:q){
							if(a.type.equals("h") && a.no==1){
								deq.health+=((2/3)*a.health);
								q.remove(a);
							}
						}
						hcount.remove(0);
						
					}
				}
				else{
					int d2=dist(deq.x,deq.y,x_sherbi,y_sherbi);	
					if(d2>5){
						deq.turnsofc+=1;
					}
					else{
						deq.turnsofc=0;
					}
					if(d2<=1){
						rad1=true;
						deq.x=x_sherbi;
						deq.y=y_sherbi;
						if(deq.no==1){
							x_fcarni=deq.x;
							y_fcarni=deq.y;
						}
						else{
							x_scarni=deq.x;
							y_scarni=deq.y;
						}
						for(Animal a:q){
							if(a.type.equals("h") && a.no==2){
								deq.health+=((2/3)*a.health);
								q.remove(a);
							}
						}
						try{
						hcount.remove(1);}
						catch(Exception e){
							;
						}
						
					}
				}
			}
			
			if(rad1==false){
				if(circle(x_g2, y_g2,deq.x ,deq.y ,r_g2 )==1 ||circle(x_g1, y_g1,deq.x ,deq.y ,r_g1 )==1){
				//carni inside grassland
					boolean verdict=prob(25);
					if(verdict){
						deq.health-=60;

					}
					else{
						if (hcount.size()==1){
							if(hcount.get(0)==1){
								
								
								int d=dist(deq.x,deq.y,x_fherbi,y_fherbi);
								deq.x=travx(deq.x, x_fherbi, 2, d);
								deq.y=travy(deq.y, y_fherbi, 2, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
								
							}
							else{
								
							
								int d=dist(deq.x,deq.y,x_sherbi,y_sherbi);
								deq.x=travx(deq.x, x_sherbi, 2, d);
								deq.y=travy(deq.y, y_sherbi, 2, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
								
							}
						}
						else{
							//both herbi alive
							int d1=dist(deq.x,deq.y,x_fherbi,y_fherbi);
							int d2=dist(deq.x,deq.y,x_sherbi,y_sherbi);
							if(d1>=d2){
								int d=dist(deq.x,deq.y,x_fherbi,y_fherbi);
								deq.x=travx(deq.x, x_fherbi, 2, d);
								deq.y=travy(deq.y, y_fherbi, 2, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
								
							}
							else{
								int d=dist(deq.x,deq.y,x_sherbi,y_sherbi);
								deq.x=travx(deq.x, x_sherbi, 2, d);
								deq.y=travy(deq.y, y_sherbi, 2, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
							}
						}
					}
				
				
				}
				else{
					//carni not inside grassland
					boolean verdict=prob(92);
					if (verdict){
						if (hcount.size()==1){
							if(hcount.get(0)==1){
								
								
								int d=dist(deq.x,deq.y,x_fherbi,y_fherbi);
								deq.x=travx(deq.x, x_fherbi, 4, d);
								deq.y=travy(deq.y, y_fherbi, 4, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
								
							}
							else{
								
							
								int d=dist(deq.x,deq.y,x_sherbi,y_sherbi);
								deq.x=travx(deq.x, x_sherbi, 4, d);
								deq.y=travy(deq.y, y_sherbi, 4, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
								
							}
						}
						else{
							//both herbi alive
							int d1=dist(deq.x,deq.y,x_fherbi,y_fherbi);
							int d2=dist(deq.x,deq.y,x_sherbi,y_sherbi);
							if(d1>=d2){
								int d=dist(deq.x,deq.y,x_fherbi,y_fherbi);
								deq.x=travx(deq.x, x_fherbi, 4, d);
								deq.y=travy(deq.y, y_fherbi, 4, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
								
							}
							else{
								int d=dist(deq.x,deq.y,x_sherbi,y_sherbi);
								deq.x=travx(deq.x, x_sherbi, 4, d);
								deq.y=travy(deq.y, y_sherbi, 4, d);
								if(deq.no==1){
									x_fcarni=deq.x;
									y_fcarni=deq.y;
								}
								else{
									x_scarni=deq.x;
									y_scarni=deq.y;
								}
							}
						}
					}
					else{
						deq.health-=60;
					}
				}
				
			}
		}
		
		if(deq.turnsofc>7){
			deq.health-=6;
		}
		turn+=1;
		System.out.println("Health after taking turn is "+deq.health);
		if(deq.health<=0){
			try{
			if(deq.no==1){
				System.out.println("First Carnivore dead");
				ccount.remove(0);
			}
			else{
				System.out.println("Second Carnivore dead");
				ccount.remove(1);
			}}
			catch (Exception e){
				;
			}
			
			q.remove(deq);
			
		}
		else{
		q.remove(deq);
		Random r = new Random();
		deq.time=(r.nextInt(ftime-1-time+1+1)+time+1);
		if(deq.time>=ftime-1){
			break;
		}
		q.add(deq);
		}
		
	}
	
	}
}
}
