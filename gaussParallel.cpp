//GAUSS METHOD

#include<iostream>
#include<stdio.h>
#include<curses.h>
#include<stdlib.h>
#include<fstream>

using namespace std;
int i,j,k,n,row,column;
double m[20][20],aux;

int main(){
 
ifstream f("matrix.txt");
f >> n;

for (i = 1; i <= n; i++){
  for (j = 1; j <= n; j++){
      f >> m[i][j];
  }
  f >> m[i][n+1];
}  

for(i=1;i<=n;i++){
  if(m[i][i]!=0){
    aux=1/m[i][i];
    for(j=1;j<=n+1;j++){
      m[i][j]=aux*m[i][j];
    }

    for(j=1;j<=n;j++){
      if(j!=i){
        aux=-m[j][i];
        for(k=1;k<=n+1;k++){
          m[j][k]=m[j][k]+aux*m[i][k];
        }
      }
    }
  }
}

cout<<"\n";
cout<<"The identity matrix is";
cout<<"\n\n";
for(i=1;i<=n;i++){
  for(j=1;j<=n;j++){
    cout<<m[i][j]<<"\t";
  }
  cout<<"\n\n";
}

cout<<"The value of unknowns is : ";
for(i=1;i<=n;i++){
  cout<<"\nX"<<i<<" = "<<m[i][n+1]<<"\n";
}

getchar();
}
