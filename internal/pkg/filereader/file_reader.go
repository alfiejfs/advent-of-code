package filereader

import (
	"bufio"
	"os"
	"strconv"
)

func ReadIntFile(relativePath string) []int64 {
	path, err := os.Getwd()
	if err != nil {
		panic(err)
	}

	file, err := os.Open(path + "\\" + relativePath)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var nums []int64

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		i, err := strconv.ParseInt(line, 10, 64)
		if err != nil {
			panic(err)
		}
		nums = append(nums, i)
	}

	return nums
}

func ReadStringFile(relativePath string) []string {
	path, err := os.Getwd()
	if err != nil {
		panic(err)
	}

	file, err := os.Open(path + "\\" + relativePath)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var lines []string

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		lines = append(lines, line)
	}

	return lines
}
