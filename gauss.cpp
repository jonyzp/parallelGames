//GAUSS METHOD

#include<iostream>
#include<stdio.h>
#include<curses.h>
#include<stdlib.h>
#include<fstream>

int i,j,k,n;
double m[20][20],aux;

int main(){

std::ifstream f("matrix.txt");
  f >> n;

for (i = 1; i <= n; i++){
  for (j = 1; j <= n; j++){
    f >> m[i][j];
  }
  f >> m[i][n+1];
}

time_t start = time(0);
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
time_t end = time(0);
double tim = difftime(end, start) * 1000.0;


std::cout<<"\n";
std::cout<<"The identity matrix is :";
std::cout<<"\n\n";
for(i=1;i<=n;i++){
  for(j=1;j<=n;j++){
    std::cout<<m[i][j]<<"\t";
  }
  std::cout<<"\n\n";
}

std::cout<<"The value of the unknowns is : ";
for(i=1;i<=n;i++){
  std::cout<<"\nX"<<i<<" = "<<m[i][n+1]<<"\n";

}
cout << "el tiempo que se tardo en encontrar la solucion fue: " << tim<<endl;

getchar();
return 1;
}
