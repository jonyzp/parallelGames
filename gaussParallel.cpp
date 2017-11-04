//GAUSS METHOD

#include <iostream>
#include <stdio.h>
#include <curses.h>
#include <stdlib.h>
#include <fstream>

using namespace std;
int i,j,k,n;
double multi,**m,*X;

void readMatrix(){
  ifstream f("matrix.txt");
  f >> n;

  m= new double *[n];
  for (i = 0; i < n; i++) {
        m[i] = new double[n+1];
  }

  X= new double [n];

  for (i = 0; i < n; i++){
    for (j = 0; j <= n; j++){
      f >> m[i][j];
    }
  }
  /**for (i = 0; i < n; i++){
    for (j = 0; j <= n; j++){
      cout << m[i][j] <<"\t";
    }
    cout << endl;
  }
  */
}

void gaussElimination(){
  for ( k= 0; k < n-1; ++k)
  {
    #pragma omp parallel for private( i,j,multi)
    for ( i = k+1; i < n; ++i)
    {
      multi= m[i][k]/m[k][k];
      m[i][k] =0;
      for (j = k+1; j< n+1; ++j)
      {
        m[i][j] = double(m[i][j]- multi* m[k][j]);
      }
    }
  }
}

void regressiveSubstitution(){
  for(int i=n-1; i>=0; --i){
    double sum = 0;
    for(int p=i; p<n; ++p){
      sum += m[i][p]*X[p];
    }
    X[i]=(m[i][n]-sum)/m[i][i];
  }  
}

void printResults(){
  std::cout<<"The value of the unknowns is : ";
  for(i=1;i<=n;i++){
    std::cout<<"\nX"<<i<<" = "<<X[i-1]<<"\n";
  }
}

int main(){
readMatrix();
time_t start = time(0);
gaussElimination();
time_t end = time(0);
double tim = difftime(end, start) * 1000.0;
regressiveSubstitution();
printResults();
cout << "el tiempo que se tardo en encontrar la solucion fue: " << tim<<endl;
}