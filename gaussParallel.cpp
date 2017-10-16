//GAUSS METHOD

#include<iostream>
#include<stdio.h>
#include<curses.h>
#include<stdlib.h>
#include<fstream>

using namespace std;
int i,j,k,n,row,column;
const int tam=10001;
double m[tam][tam],aux, X[tam];

int main(){

ifstream f("matrix.txt");
f >> n;

for (i = 0; i < n; i++){
  for (j = 0; j < n; j++){
      f >> m[i][j];
  }
  f >> m[i][n];
}

time_t start = time(0);
#pragma omp parallel for private( k,i,j,aux)
for(k=0; k<n; ++k){
    for(i=k+1; i<n; ++i){
      aux = m[i][k]/m[k][k];
      m[i][k] =0;
      for(j=k+1; j<=n; ++j){
        m[i][j] = double(m[i][j] - aux*m[k][j]);
      }
    }
  }
#pragma omp parallel for
for(int i=n-1; i>=0; --i){
  double sum = 0;
  for(int p=i+1; p<n; ++p){
    sum += m[i][p]*X[p];
  }
  X[i]=(m[i][n]-sum)/m[i][i];
}

time_t end = time(0);
double tim = difftime(end, start) * 1000.0;

/*
cout<<"The identity matrix is";
cout<<"\n\n";
for(i=0;i<n;i++){
  for(j=0;j<n+1;j++){
    cout<<m[i][j]<<"\t";
  }
  cout<<"\n\n";
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
*/
cout<<"The value of unknowns is : ";
for(i=1;i<=n;i++){
  cout<<"\nX "<<i<<" = "<<X[i]<<"\n";
}

cout << tim;

getchar();
}
