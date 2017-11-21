#include <iostream>
#include <stdio.h>
#include <curses.h>
#include <stdlib.h>
#include <fstream>

using namespace std;
int i,j,k,n;
double multi,**m,*X;

//Here the matrix's readen
void readMatrix(){
  ifstream f("matrix.txt");
  f >> n;

  m = new double *[n];
  for (i = 0; i < n; i++) {
    m[i] = new double[n+1];
  }

  X = new double [n];

  for (i = 0; i < n; i++){
    for (j = 0; j <= n; j++){
      f >> m[i][j];
    }
  }

}

//Here we parallelize the gauss elimination method with the library openmp
void gaussElimination(){
  for (k = 0; k < n-1; ++k){
    //With this line we put private some of the variables to parallelize the method
    #pragma omp parallel for private(i,j,multi)
    for ( i = k+1; i < n; ++i){
      multi = m[i][k]/m[k][k];
      m[i][k] = 0;
      for(j = k+1; j < n+1; ++j){
        m[i][j] = double(m[i][j] - multi * m[k][j]);
      }
    }
  }
}

//Here the regressive function is ejecuted
void regressiveSubstitution(){
  for(int i=n-1; i>=0; --i){
    double sum = 0;
    for(int p=i; p<n; ++p){
      sum += m[i][p]*X[p];
    }
    X[i]=(m[i][n]-sum)/m[i][i];
  }
}

//Here we print the solution and put the value of the unknowns
void printResults(){
  std::cout<<"The value of the unknowns is: ";
  for(i=1;i<=n;i++){
    std::cout<<"\nX"<<i<<" = "<<X[i-1]<<"\n";
  }
}

//This is the main method that ejecuted all the methods of the class
int main(){
  readMatrix();
  time_t start = time(0);
  gaussElimination();
  time_t end = time(0);
  double tim = difftime(end, start) * 1000.0;
  regressiveSubstitution();
  printResults();
  cout << "It took this time to get the solution: " << tim<<endl;
}
