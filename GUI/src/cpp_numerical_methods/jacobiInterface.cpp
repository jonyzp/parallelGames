#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <cmath>

using namespace std;

vector<double> xValues,xNewValues;
double**matrix;

void printSolution(){    
    cout << "X Vector "<<endl;   
    for (int i = 0; i <xValues.size(); ++i){
        //cout << Z[i] << endl;
        printf("%.10g  ", xValues[i]);
    }    
}



int initializeMatrix(string origin){
    ifstream read;
    read.open(origin.c_str());
    int size = 0;
    read >> size;
    matrix = new double *[size];
    for (int i = 0; i < size; ++i){
        matrix[i] = new double [size+1];
    }

    for (int i = 0; i < size; ++i){
        for (int j = 0; j < size + 1; ++j){
            read >> matrix[i][j];
        }
    }
    return size;
}


double newJacobi(int matrixSize){
    double suma, disp,var, aii;
    disp = 0;
    for (int i = 0; i < matrixSize;++i){
        suma = 0;
        for (int j = 0; j < matrixSize;++j){
            var = matrix[i][j];
            if (i!=j)
                suma += var*xValues[j];
            else
                aii = var;
        }
        var = matrix[i][matrixSize];
        xNewValues[i] = (var - suma)/aii;
        disp = max(disp, abs(xNewValues[i]- xValues[i]));
    }

    return disp;
}

bool jacobi(long long matrixSize, double tol, long long niter){
    double disp = tol+1;
    int cont = 0;

    while (disp > tol && cont < niter){
        for (int i = 0; i < matrixSize; ++i)xValues[i] = xNewValues[i];
        disp = newJacobi(matrixSize);
        cont++;

        cout << " iterations: " << cont<< endl; 
        printSolution();
        cout << " | error: " << disp<< endl;
    }
    cout << endl;

    if (disp <= tol)
        return true;
    return false;
}



int main(){
    std::ios::sync_with_stdio(false);
    string matrixInputFile, outputFile;
    long long matrixSize, maxIterations;
    double tolerance;
    bool success;
    matrixInputFile = "matrix.txt";    
    matrixSize = initializeMatrix(matrixInputFile);
    xValues.assign(matrixSize, 0);
    xNewValues.assign(matrixSize, 0);
    cout << "tolerance? g.e 0.01" << endl;
    cin >> tolerance;
    cout << "iterations? g.e: 100" << endl;
    cin >> maxIterations;
    success = jacobi(matrixSize, tolerance, maxIterations);
    if (success){
        cout <<"solution: "<<endl;
        printSolution();
    }else  cout << "could not reach the solutions in " << maxIterations << " iterations" << endl;
    return 0;
}
