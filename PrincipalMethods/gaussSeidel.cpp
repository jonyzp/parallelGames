#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cmath>
#include <time.h>
using namespace std;

vector<double> independents;
double**matrix;

//Here we find the aproximation of the solution, we calculate the newXValue,
//the error and the independents.
double sIteration(int mSize){
    double suma, error, var, aii, newXValue;
    error = 0;
    for (int i = 0; i < mSize;++i){
        suma = 0;
        for (int j = 0; j < mSize;++j){
            var = matrix[i][j];
            if (i!=j)
                suma += var*independents[j];
            else
                aii = var;
        }
        var = matrix[i][mSize];
        newXValue =  (var - suma)/aii;
        error = max(error, abs(newXValue - independents[i]));
        independents[i] = newXValue;
    }
    return error;
}

//Here we know if we found the solution of the matrix or if we have to stop the process
bool gaussS(long long mSize, double tol, long long niter){
    double error = tol+1;
    int cont = 0;

    while (error > tol && cont < niter){
        error = sIteration(mSize);
        cont++;
    }

    if (error <= tol)
        return true;
    return false;
}

//Here we write the solution of the system
void writeSolution(string solutionFile){
    ofstream write;
    write.open(solutionFile.c_str(),ios::trunc);
    for (int i = 0; i < independents.size();++i){
        write << independents[i] << endl;
    }
    write.close();
}

//This is the main method that ejecuted all the methods of the class
//And here we put the tolerance and iterations of the method
int main(){
    std::ios::sync_with_stdio(false);
    string originalFile,readingName,writingName, solutionFile;
    long long mSize, iterations;
    clock_t start, end;
    double tolerance;
    bool res;
    std::ifstream f("matrix.txt");
      f >> mSize;
      matrix = new double *[mSize];
      for (int i = 0; i < mSize; ++i){
          matrix[i] = new double [mSize+1];
      }

      for (int i = 0; i < mSize; ++i){
          for (int j = 0; j < mSize + 1; ++j){
              f >> matrix[i][j];
          }
      }

    solutionFile="solution.txt";
    independents.assign(mSize, 0);


    tolerance=0.000001;
    iterations=10000;
    start = clock();

    res = gaussS(mSize, tolerance, iterations);

    end = clock();
    printf("The time was: %.30g\n", (double)( (end - start) / 1000.0));

    if (res)
        writeSolution(solutionFile);
    else
        cout << "could not reach the solutions in " << iterations << " iterations" << endl;
    writeSolution(solutionFile);

    return 0;
}
