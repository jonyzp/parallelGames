#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <cmath>
#include <time.h>

using namespace std;

vector<double> xValues,xNewValues;
double**matrix;

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
    int i,j;
    disp = 0;
    #pragma omp parallel for shared(xValues, xNewValues, matrix, matrixSize, disp) private(i, j, var, suma, aii)
    for (i = 0; i < matrixSize;++i){
        suma = 0;
        for (j = 0; j < matrixSize;++j){
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
        for (int i = 0; i < matrixSize; ++i){
            xValues[i] = xNewValues[i];
        }
        disp = newJacobi(matrixSize);
        cont++;
    }

    if (disp <= tol)
        return true;
    return false;
}


void writeSolution(string outputFile){
    ofstream write;
    write.open(outputFile.c_str(),ios::trunc);
    for (int i = 0; i < xValues.size();++i){
        write << xValues[i] << endl;
    }
    write.close();
}

int main(){
    std::ios::sync_with_stdio(false);
    string matrixInputFile, outputFile;
    long long matrixSize, maxIterations;
    clock_t start, end;
    double tolerance;
    bool success;

    matrixInputFile = "./matrices/matrix1000.txt";
    outputFile = "solution.txt";

    matrixSize = initializeMatrix(matrixInputFile);

    xValues.assign(matrixSize, 0);
    xNewValues.assign(matrixSize, 0);

    //cout << "how much tolerance? for example if you have n=10, tolerance should be 0.01 and maxIters=10000" << endl;
    //cin >> tolerance;
    //cout << "how many iterations? i.e: 100" << endl;
    //cin >> maxIterations;

    tolerance=0.000001;
    maxIterations=10000;   
    start = clock(); 
    success = jacobi(matrixSize, tolerance, maxIterations);
    end = clock(); 
    printf("The time was: %.30g\n", (double)( (end - start) / 1000.0)); 
    
    if (success)
        writeSolution(outputFile);
    else
        cout << "could not reach the solutions in " << maxIterations << " iterations" << endl;
        writeSolution(outputFile);
    return 0;
}
