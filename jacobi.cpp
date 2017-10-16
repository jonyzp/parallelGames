#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

vector<double> xValues,xNewValues;
double**matrix;

int initialMatrixProcessing(string origin){
    ifstream read;
    stringstream ss;
    string firstLine;
    int cont;
    long long var;

    read.open(origin.c_str());

    getline(read, firstLine);
    ss.str(firstLine);
    
    cont = 0;
    while (ss >> var) cont++;
    read.close();
    return cont-1;
}

int readMatrix(string origin){
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

double jacobiIteration(int matrixSize){
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
        disp = jacobiIteration(matrixSize);
        cont++;
    }

    if (disp <= tol)
        return true;
    return false;
}

void printSolution(string solutionFile){
    ofstream write;
    write.open(solutionFile.c_str(),ios::trunc);
    for (int i = 0; i < xValues.size();++i){
        write << xValues[i] << endl;
    }
    write.close();
}

int main(){
    std::ios::sync_with_stdio(false);
    string originalFile,readingName,writingName, solutionFile;
    long long matrixSize, iterations;
    double tolerance;
    bool res;

    originalFile = "matrix.txt";
    solutionFile = "sol.txt";

    matrixSize = readMatrix(originalFile);

    xValues.assign(matrixSize, 0);
    xNewValues.assign(matrixSize, 0);

    cout << "how much tolerance? example: 0.0001" << endl;
    cin >> tolerance;
    cout << "how many iterations? i.e: 100" << endl;
    cin >> iterations;

    res = jacobi(matrixSize, tolerance, iterations);
    
    if (res)
        printSolution(solutionFile);
    else
        cout << "could not reach the solutions in " << iterations << " iterations" << endl;
        
    return 0;
}
