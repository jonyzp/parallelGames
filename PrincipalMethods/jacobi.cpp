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

//Here we find the aproximation of the solution, we calculate the newXValue,
//the error and the independents.
double jacobiIterations(int matrixSize){
    double suma, error,var, aii;
    error = 0;
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
        error = max(error, abs(xNewValues[i]- xValues[i]));
    }
    return error;
}

//Here we know if we found the solution of the matrix or if we have to stop the process
bool jacobi(long long matrixSize, double tol, long long niter){
    double error = tol+1;
    int cont = 0;

    while (error > tol && cont < niter){
        for (int i = 0; i < matrixSize; ++i)xValues[i] = xNewValues[i];
        error = jacobiIterations(matrixSize);
        cont++;
    }

    if (error <= tol)
        return true;
    return false;
}

//Here we write the solution of the system
void writeSolution(string outputFile){
    ofstream write;
    write.open(outputFile.c_str(),ios::trunc);
    for (int i = 0; i < xValues.size();++i){
        write << xValues[i] << endl;
    }
    write.close();
}

//This is the main method that ejecuted all the methods of the class
//And here we put the tolerance and iterations of the method
int main(){
    std::ios::sync_with_stdio(false);
    string matrixInputFile, outputFile;
    long long matrixSize, maxIterations;
    clock_t start, end;
    double tolerance;
    bool success;

    matrixInputFile = "matrix.txt";
    outputFile = "solution.txt";

    matrixSize = initializeMatrix(matrixInputFile);

    xValues.assign(matrixSize, 0);
    xNewValues.assign(matrixSize, 0);

    tolerance=0.000001;
    maxIterations=15000;
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
