package main

import (
	"fmt"
	"github.com/alfiejsmith/advent-of-code/internal/pkg/filereader"
)

func getEncounteredTrees(gridp *[][]string, across int, down int) int {
	grid := *gridp

	xPos, yPos, encounteredTrees := 0, 0, 0

	for yPos < len(grid) {

		if xPos >= len(grid[yPos]) {
			xPos -= len(grid[yPos])
		}

		if grid[yPos][xPos] == "#" {
			encounteredTrees++
		}

		xPos += across
		yPos += down
	}

	return encounteredTrees
}

func main() {
	data := filereader.ReadStringFile("input")

	grid := make([][]string, len(data), len(data))

	for y := 0; y < len(data); y++ {
		line := data[y]
		grid[y] = make([]string, len(line), len(line))
		for x := 0; x < len(line); x++ {
			grid[y][x] = string(line[x])
		}
	}


	trees := getEncounteredTrees(&grid, 3, 1)


	fmt.Println("Part One:", trees)

	trees *= getEncounteredTrees(&grid, 1, 1)
	trees *= getEncounteredTrees(&grid, 5, 1)
	trees *= getEncounteredTrees(&grid, 7, 1)
	trees *= getEncounteredTrees(&grid, 1, 2)

	fmt.Println("Part Two:", trees)
}