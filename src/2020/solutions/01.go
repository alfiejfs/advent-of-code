package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func checkError(err error) {
	if err != nil {
		panic(err)
	}
}

func main() {

	path, err := os.Getwd()
	checkError(err)

	file, err := os.Open(path + "\\..\\inputs\\01.txt")
	checkError(err)
	defer file.Close()

	var nums []int64

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		i, err := strconv.ParseInt(line, 10, 64)
		checkError(err)
		nums = append(nums, i)
	}

	var target int64 = 2020

	PartOne:
		for i := 0; i < len(nums); i++ {
			for j := i; j < len(nums); j++ {
				if nums[i] + nums[j] == target {
					fmt.Println("Part One:", nums[i] * nums[j])
					break PartOne
				}
			}
		}

	PartTwo:
		for i := 0; i < len(nums); i++ {
			for j := i; j < len(nums); j++ {
				for k := j; k < len(nums); k++ {
					if nums[i] + nums[j] + nums[k] == target {
						fmt.Println("Part Two:", nums[i] * nums[j] * nums[k])
						break PartTwo
					}
				}
			}
		}

}
