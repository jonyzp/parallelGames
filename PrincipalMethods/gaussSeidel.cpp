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

double sIteration(int mSize){
    double suma, disp,var, aii, newXValue;
    disp = 0;
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
        disp = max(disp, abs(newXValue - independents[i]));
        independents[i] = newXValue;
    }
    return disp;
}

bool gaussS(long long mSize, double tol, long long niter){
    double disp = tol+1;
    int cont = 0;

    while (disp > tol && cont < niter){
        disp = sIteration(mSize);
        cont++;
    }

    if (disp <= tol)
        return true;
    return false;
}

void writeSolution(string solutionFile){
    ofstream write;
    write.open(solutionFile.c_str(),ios::trunc);
    for (int i = 0; i < independents.size();++i){
        write << independents[i] << endl;
    }
    write.close();
}

int main(){
    std::ios::sync_with_stdio(false);
    string originalFile,readingName,writingName, solutionFile;
    long long mSize, iterations;
    clock_t start, end;
    double tolerance;
    bool res;
    std::ifstream f("matrix5000.txt");
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
    //cout << "What's the name of the file where you want the solution be?" << endl;
    //cin >> solutionFile;
      solutionFile="sol.txt";
    independents.assign(mSize, 0);

    //cout << "How much tolerance?" << endl;
    //cin >> tolerance;
    //cout << "How many iterations?" << endl;
    //cin >> iterations;


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

