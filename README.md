https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

It’s a simple 2-dimensional automata where each cell can be either alive or dead. 

Each “clock tick” updates the live/dead status of each cell based on the following rules: 

* Any live cell with fewer than two live neighbors dies, as if by underpopulation.

* Any live cell with two or three live neighbors lives on to the next generation.

* Any live cell with more than three live neighbors dies, as if by overpopulation.]

* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction. 


Your code should demonstrate its correct with this test: 

1st generation: [ [0,1,0], [0,1,0], [0,1,0] ]

2nd generation: [ [0,0,0], [1,1,1], [0,0,0] ]

3rd generation: [ [0,1,0], [0,1,0], [0,1,0] ]
