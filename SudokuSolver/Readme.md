# Sudoku Solver

* Este Proyecto permite ejecutar un solucionador de Sudokus paralelo:

Sudoku 3: 

Compilación: `ghc -O2 sudoku3.hs -threaded -rtsopts -eventlog`
Ejecución: `./sudoku3 sudoku17.1000.txt +RTS -N4 -l`
Eventlog: `threadscope sudoku2.eventlog`

Sudoku 2:

$ ghc -O2 sudoku2.hs -rtsopts -threaded
$ ./sudoku2 sudoku17.1000.txt +RTS -N2 -s

ó 

$ rm sudoku2; ghc -O2 sudoku2.hs -threaded -rtsopts -eventlog
$ ./sudoku2 sudoku17.1000.txt +RTS -N2 -l
1000
$ threadscope sudoku2.eventlog

### Instalación de Haskell:

`sudo apt-get install haskell-platform`

Si quieres ejecutar el interprete: `ghci`

Prelude> let fac n = if n == 0 then 1 else n * fac (n-1)
Prelude> fac 42
1405006117752879898543142606244511569936384000000000

# Learning:

El primer archivo para probar es helloWorld.hs

-- Para compilar:
-- $ ghc -o hello helloWorld.hs
-- Ejecucion:
-- ./hello

A.hs es un código paralelizado
compilar: `ghc -O2 --make A.hs -threaded -rtsopts`
probar: `time ./A +RTS -N2`   -N4 para usar 4 cores

** Más info ** https://wiki.haskell.org/Haskell_in_5_steps

Sudoku.hs es la biblioteca que puede ser importada
los .txt son los archivos de prueba

sudoku1: 
`ghc -O2 sudoku1.hs -rtsopts`
`./sudoku1 sudoku17.1000.txt +RTS -s`

Todo lo saqué del libro: http://chimera.labs.oreilly.com/books/1230000000929/ch02.html#sec_par-eval-sudoku2 es muy explicativo
Y del repo: https://github.com/zhangjiji/parallel-and-concurrent-programming-in-haskell/tree/master/ch2
Diapositivas que lo explican: https://absalon.ku.dk/files/830955/download

Aqui está el sudoku solver en python: https://pythontips.com/2013/09/01/sudoku-solver-in-python/

Puede servir: https://wiki.haskell.org/Sudoku

Haskell Docs: https://www.haskell.org/documentation