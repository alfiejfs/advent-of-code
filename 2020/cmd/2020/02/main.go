package main

import (
	"fmt"
	"github.com/alfiejsmith/advent-of-code/internal/pkg/filereader"
	"strconv"
	"strings"
)

type rule struct {
	character string
	lower uint8
	upper uint8
}

func (r *rule) isValidPartOne(pass string) bool {
	amount := uint8(strings.Count(pass, r.character))
	return amount >= r.lower && amount <= r.upper
}

func (r *rule) isValidPartTwo(pass string) bool {
	passing := false

	if string(pass[r.lower - 1]) == r.character {
		passing = true
	}

	if string(pass[r.upper - 1]) == r.character {
		passing = !passing
	}

	return passing
}

func isValid(data string, validCheck func(r *rule, s string) bool) bool {
	rulePassSplit := strings.Split(data, ": ")

	ruleRaw := rulePassSplit[0]

	ruleSplit := strings.Split(ruleRaw , " ")

	lowerUpperSplit := strings.Split(ruleSplit[0], "-")

	lower, err := strconv.ParseUint(lowerUpperSplit[0], 10, 8)
	if err != nil {
		panic(err)
	}
	upper, err := strconv.ParseUint(lowerUpperSplit[1], 10, 8)
	if err != nil {
		panic(err)
	}

	r := rule{
		character:ruleSplit[1],
		lower: uint8(lower),
		upper: uint8(upper),
	}

	pass := rulePassSplit[1]

	return validCheck(&r, pass)
}

func main() {
	passwords := filereader.ReadStringFile("input")

	solve(&passwords)
}

func solve(passwords *[]string) {
	validCountPartOne := 0
	validCountPartTwo := 0

	for _, password := range *passwords {
		if isValid(password, (*rule).isValidPartOne) {
			validCountPartOne++
		}
		if isValid(password, (*rule).isValidPartTwo) {
			validCountPartTwo++
		}
	}

	fmt.Println("Part one:", validCountPartOne)
	fmt.Println("Part two:", validCountPartTwo)
}