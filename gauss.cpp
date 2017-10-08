//METODO DE GAUSS

#include<iostream>
#include<stdio.h>
#include<curses.h>
#include<stdlib.h>
int i,j,k,n;
double m[20][20],aux;

int main(){
  std::cout<<"        <<Gaussian Method>>         ";
  std::cout<<"\n Square matrix of order N= ";
  std::cin>>n;
  std::cout<<"\n Type the matrix's elements ";
  for(i=1;i<=n;i++){
    for(j=1;j<=n;j++){
      std::cout<<"\n M=["<<i<<","<<j<<"]= ";
      std::cin>>m[i][j];
    }
    std::cout<<"\n Independent term of X"<<i<<" ";
    std::cin>>m[i][n+1];
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

std::cout<<"\n";
std::cout<<"The identity matrix is";
std::cout<<"\n\n";
for(i=1;i<=n;i++){
  for(j=1;j<=n;j++){
    std::cout<<m[i][j]<<"\t";
  }
  std::cout<<"\n\n";
}

std::cout<<"The value of unknowns is : ";
for(i=1;i<=n;i++){
  std::cout<<"\nX"<<i<<" = "<<m[i][n+1]<<"\n";
}

getchar();
}
