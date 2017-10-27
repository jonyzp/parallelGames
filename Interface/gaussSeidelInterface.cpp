#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

vector<double> independents;
double**matrix;



void printSolution(){    
    cout << "X Vector "<<endl;   
    for (int i = 0; i <independents.size(); ++i){
        //cout << Z[i] << endl;
        printf("%.10g  ", independents[i]);
    }    
}


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
        cout << " iterations: " << cont<< endl;
        printSolution();
        cout << " | error: " << disp<< endl;
    }

    if (disp <= tol)
        return true;
    return false;
}

int main(){
    std::ios::sync_with_stdio(false);
    string originalFile,readingName,writingName;
    long long mSize, iterations;
    double tolerance;
    bool success;
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
    independents.assign(mSize, 0);
    cout << "tolerance? g.e 0.01" << endl;
    cin >> tolerance;
    cout << "iterations? g.e: 100" << endl;
    cin >> iterations;
    success = gaussS(mSize, tolerance, iterations);
    if (success){
        cout <<"solution: "<<endl;
        printSolution();
    }else  cout << "could not reach the solutions in " << iterations << " iterations" << endl;

    return 0;
}
