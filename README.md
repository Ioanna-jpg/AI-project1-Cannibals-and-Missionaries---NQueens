### AI-project1-Cannibals-and-Missionaries & NQueens
The Cannibals and Missionaries problem is a classic AI puzzle: three missionaries and three cannibals must cross a river using a boat which can carry at most two people.  
N-Queen problem is a classical problem in the field of AI, where we try to find a chessboard configuration where in a N × N board there will be N queens, not attacking each other.

**Exercise 1: Missionaries and cannibals problem:**

<ins>The problem is solved using an A* algorithm</ins>

A few words about the problem:
three missionaries and three cannibals must cross a river
using a boat capable of carrying not more than two persons, under
restriction that, for both parties, if there be missionaries on shore, no
can be more than cannibals (if they were, cannibals would
they ate the missionaries). The boat cannot cross the river alone
of without passengers.

The generalized problem of cannibals and missionaries:
- The problem we solved accepts as variables the values of
of cannibals and missionaries (N), of the boat's capacity (M) and of
maximum allowed number of crossings of the boat (K) by
user.
- We consider that in the initial situation we have N missionaries at one time
bank and the same number (N) of cannibals on the same bank
- The program finds the optimal solution that does not exceed K
crossings, if such a solution exists. Also mention how much time
(depending on the computer) it needs the program to find a solution
for the individual values of N, M, K, as well as the corresponding values
solutions he finds.


**Exercise 2: NQueens problem**

<ins>The problem is solved using a genetic algorithm</ins>

A few words about the problem:
On a board of size 8*8 we have to place 8 queens in such a way
so that no queen is threatened.

The generalized problem of N queens:
-The problem we solved accepts as variables its size
chessboard which is the same as the number of queens
-We consider that in the initial state we have random boards with random
placed up the queens along the x-axis.
-The program finds one of the solutions. Also state approximately how much
time (depending on the computer) the program needs to find
solution for the individual values of N each time as well as displays the
correct board.

