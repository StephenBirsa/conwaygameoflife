# conwaygameoflife
Conway's Game of Life made in Java

One seed config:

Gosper glider gun

![image](https://user-images.githubusercontent.com/27788517/201854977-80a3efb8-1710-466d-a20a-9b36d4f3643b.png)


		//Block 
    		grid[100][100].isLiving = true;
		grid[101][100].isLiving = true;
		grid[100][101].isLiving = true;
		grid[101][101].isLiving = true;
		
		//first glide
		grid[110][100].isLiving = true;
		grid[110][101].isLiving = true;
		grid[110][102].isLiving = true;

		grid[111][99].isLiving = true;
		grid[111][103].isLiving = true;

		grid[112][98].isLiving = true;
		grid[112][104].isLiving = true;
		grid[113][98].isLiving = true;
		grid[113][104].isLiving = true;
		
		grid[114][101].isLiving = true;

		grid[115][99].isLiving = true;
		grid[115][103].isLiving = true;
		
		grid[116][100].isLiving = true;
		grid[116][101].isLiving = true;
		grid[116][102].isLiving = true;

		grid[117][101].isLiving = true;
		
		//second glide
		grid[120][100].isLiving = true;
		grid[120][99].isLiving = true;
		grid[120][98].isLiving = true;
		grid[121][100].isLiving = true;
		grid[121][99].isLiving = true;
		grid[121][98].isLiving = true;
		
		grid[122][97].isLiving = true;
		grid[122][101].isLiving = true;
		grid[124][96].isLiving = true;
		grid[124][97].isLiving = true;
		grid[124][101].isLiving = true;
		grid[124][102].isLiving = true;
		
		//Block
		grid[134][99].isLiving = true;
		grid[134][98].isLiving = true;
		grid[135][99].isLiving = true;
		grid[135][98].isLiving = true;
