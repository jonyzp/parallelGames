#include <stdio.h>
#include <fstream>
#include <iostream>

using namespace std;

int main()
{
    int i,j,k,n;
    float A[101][101],multiplier,x[101];
    ifstream read;
    read.open("matrix.txt");
    read >> n;
    
    for(i=1; i<=n; i++)
        for(j=1; j<=(n+1); j++)
            read >> A[i][j];
    /*
    cout<<"The identity matrix is :";
	cout<<"\n\n";
	for(i=1;i<=n;i++){
	  for(j=1;j<=(n+1);j++){
	    cout<<A[i][j]<<"\t";
	  }
	  cout<<"\n\n";
	}*/
    //Buscar los elementos de la matriz diagonal
    for(j=1; j<=n; j++)
    {
        for(i=1; i<=n; i++)
        {
            if(i!=j)
            {
                multiplier = A[i][j]/A[j][j];
                for(k=1; k<=n+1; k++)
                {
                    A[i][k]=A[i][k]-(multiplier*A[j][k]);
                }
            }
        }
        x[j]=A[j][n+1]/A[j][j];
    }
    cout << endl << "The solution is:" << endl;
    for(i=1; i<=n; i++)
    {
        cout << "x" << i << " = " << x[i] << endl;
    }
    return(0);
}