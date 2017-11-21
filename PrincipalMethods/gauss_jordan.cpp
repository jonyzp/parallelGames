#include <stdio.h>
#include <fstream>
#include <iostream>

using namespace std;

int main()
{
    int i,j,k,n;
    float A[2001][2001],multiplier,x[2001];
    ifstream read;
    read.open("matrix.txt");
    read >> n;

    for(i=1; i<=n; i++)
        for(j=1; j<=(n+1); j++)
            read >> A[i][j];

    //Find the elements of the diagonal matrix
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
