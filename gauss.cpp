//METODO DE GAUSS

#include<iostream>
#include<stdio.h>
#include<curses.h>
#include<stdlib.h>
#include<fstream>

int i,j,k,n;
double m[20][20],aux;

int main()
{

/**cout<<"        <<<<Metodo de Gauss >>>>         ";
cout<<"\n Matriz cuadrada de orden N= ";
cin>>n;
cout<<"\n Digite los elementos de la matriz en la posicion ";
for(i=1;i<=n;i++)
{
for(j=1;j<=n;j++)
{
cout<<"\n M=["<<i<<","<<j<<"]= ";
cin>>m[i][j];
}
cout<<"\n Termino independiente de X"<<i<<" ";
cin>>m[i][n+1];

}*/
std::ifstream f("matrix.txt");
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
if(j!=i)
{
aux=-m[j][i];
for(k=1;k<=n+1;k++)
{
m[j][k]=m[j][k]+aux*m[i][k];
}
}
}
}
}

std::cout<<"\n";
std::cout<<"la matriz identidad es";
std::cout<<"\n\n";
for(i=1;i<=n;i++)
{
for(j=1;j<=n;j++)
{
std::cout<<m[i][j]<<"\t";
}
std::cout<<"\n\n";
}

std::cout<<"El valor de las incognitas es : ";
for(i=1;i<=n;i++)
{

std::cout<<"\nX"<<i<<" = "<<m[i][n+1]<<"\n";
}
getchar();
return 1;
}
