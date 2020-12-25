package main

import (
	"fmt"
	"github.com/alfiejsmith/advent-of-code/internal/pkg/filereader"
	"math"
)

type seat struct {
	row    int
	column int
	id     int
}

func getSeatInfo(line string) *seat {
	lowRow, highRow, lowColumn, highColumn := 0, 127, 0, 7

	for charIndex := 0; charIndex < 6; charIndex++ {
		switch line[charIndex] {
		case 'F':
			highRow -= int(math.Ceil(float64(highRow-lowRow) / 2))
		case 'B':
			lowRow += int(math.Ceil(float64(highRow-lowRow) / 2))
		}
	}

	for charIndex := 7; charIndex < len(line)-1; charIndex++ {
		switch line[charIndex] {
		case 'L':
			highColumn -= int(math.Ceil(float64(highColumn-lowColumn) / 2))
		case 'R':
			lowColumn += int(math.Ceil(float64(highColumn-lowColumn) / 2))
		}
	}

	var row int
	if line[6] == 'F' {
		row = lowRow
	} else {
		row = highRow
	}

	var column int
	if line[len(line)-1] == 'L' {
		column = lowColumn
	} else {
		column = highColumn
	}

	return &seat{
		row: row,
		column: column,
		id:row*8 + column,
	}
}

func getHighestId(input *[]string) int {
	highestId := -1

	for _, line := range *input {
		id := getSeatInfo(line).id
		if id > highestId {
			highestId = id
		}
	}

	return highestId
}

func getMyId(input *[]string) int {

	ids := make(map[int]bool)
	lowestId, highestId := 9999, 0

	for _, line := range *input {
		data := getSeatInfo(line)
		id := data.id
		ids[id] = true

		if lowestId > id {
			lowestId = id
		}
		if highestId < id {
			highestId = id
		}
	}

	for i := lowestId + 1; i < highestId; i++ {
		if _, ok := ids[i]; !ok {
			if _, ok := ids[i - 1]; ok {
				if _, ok := ids[i + 1]; ok {
					return i
				}
			}
		}
	}



	return 0
}

func main() {
	rawInput := filereader.ReadStringFile("input")

	fmt.Println("Part One:", getHighestId(&rawInput))
	fmt.Println("Part Two:", getMyId(&rawInput))
}
