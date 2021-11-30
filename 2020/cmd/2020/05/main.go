package main

import (
	"fmt"
	"github.com/alfiejsmith/advent-of-code/internal/pkg/filereader"
	"strconv"
	"strings"
)

func getSeatId(line string) int {

	line = strings.ReplaceAll(line, "B", "1")
	line = strings.ReplaceAll(line, "F", "0")
	line = strings.ReplaceAll(line, "R", "1")
	line = strings.ReplaceAll(line, "L", "0")

	id, err := strconv.ParseInt(line, 2, 64)
	if err != nil {
		panic(err)
	}

	return int(id)
}

func getHighestId(input *[]string) int {
	highestId := -1

	for _, line := range *input {
		id := getSeatId(line)
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
		id := getSeatId(line)
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
