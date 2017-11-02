
#include <bits/stdc++.h>
#include <math.h>
using namespace std;

double **L, **U, *B, *Z, *X, **A;


void printMatrix( int size){
    cout << "L Matrix "<<endl;
    for (int i = 0; i <size; ++i){
        for (int j =0 ; j<size; ++j){
            cout << L[i][j] <<" ";
        }
        cout << endl;
    }
    cout << endl;
    cout << "U Matrix "<<endl;
    for (int i = 0; i <size; ++i){
        for (int j =0 ; j<size; ++j){
            cout << U[i][j]<<" " ;
        }
        cout << endl;
    }
    cout << endl;    
}
void fillMatrix( int size){
    for (int i = 0; i <size; ++i){
        for (int j =0 ; j<size; ++j){
            L[i][j] =0;
        }
    }
    for (int i = 0; i <size; ++i){
        for (int j =0 ; j<size; ++j){
            U[i][j]=0 ;
        }
    }
}

void readMatrix( int size,string origin){
    B = new double[size];
    U = new double *[size];
    A= new double *[size];
    for (int i = 0; i < size; ++i){
        U[i] = new double [size];
    }

    for (int i = 0; i < size; ++i){
        A[i] = new double [size];
    }

    L = new double*[size];
    
    for (int i = 0; i < size; ++i){
        L[i] = new double[size];
    }

    fillMatrix(size);
    ifstream read;
    read.open(origin.c_str());
    read >> B[0];
    for (int i = 0; i < size; ++i){
        for (int j = 0; j < size; ++j){
            read >> A[i][j];
        }
        read >> B[i];
    }
    
    read.close();
}

void cholesky(int n){
    long long suma1,suma2,suma3;
    
    for(int k=0;k<n;++k){
        suma1=0;
        for(int m=0;m<k;++m){
            suma1= suma1+ (L[k][m]*U[m][k]);
        }
        L[k][k]=sqrt(A[k][k]-suma1);
        U[k][k]=L[k][k];

        for(int i=k+1;i<n;++i){
            suma2=0;
            for(int p=1;p<k;++p){
                suma2+=L[i][p]*U[p][k];
            }
            L[i][k]=(A[i][k]-suma2)/L[k][k];
        }
        
        for(int j=k+1;j<n;++j){
            suma3=0;
            for(int h=1;h<k-1;++h){
                suma3+=L[k][h]*U[h][j];
            }
            U[k][j]=(A[k][j]-suma3)/L[k][k];
        }
        cout<< "Iter: " << k <<endl;
        printMatrix(n);
    }
}


void progressiveC(int n){
    Z = new double[n];
    for(int i=0; i<n; i++){
        double sum = 0;
        for(int p=0; p<i; ++p){
            sum += L[i][p]*Z[p];
        }
        Z[i]=(B[i]-sum)/L[i][i];
    }
}

void regressiveC(int n){
    X = new double[n];
    for(int i=n-1; i>=0; --i){
        double sum = 0;
        for(int p=i+1; p<n; ++p){
            sum += U[i][p]*X[p];
        }
        X[i]=(Z[i]-sum)/U[i][i];
    }
}

void printSolution(int size){
    
    cout << "Z Vector "<<endl;   
    for (int i = 0; i <size; ++i){
        //cout << Z[i] << endl;
        printf("%.10g\n", Z[i]);
    }    
    cout << "X vector "<<endl;
    for (int i = 0; i <size; ++i){
        //cout << X[i] << endl;
        printf("%.10g\n", X[i]);
    }
    
}

int main(){
    string originalFile,readingName,writingName;
    long long matrixSize;
    originalFile="matrix.txt";
    ifstream f("matrix.txt");
    f >> matrixSize; 
    readMatrix(matrixSize,originalFile);
    cholesky(matrixSize);
    progressiveC(matrixSize);
    regressiveC(matrixSize);
    printSolution( matrixSize);
    return 0;
}

