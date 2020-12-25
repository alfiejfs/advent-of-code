package main

import (
	"fmt"
	"github.com/alfiejsmith/advent-of-code/internal/pkg/filereader"
	"regexp"
	"strconv"
	"strings"
)

const MaxFieldCount = 8

var intFields = map[string]bool {
	"byr": true,
	"iyr": true,
	"eyr": true,
}

var eyeColours = map[string]bool {
	"amb": true,
	"blu": true,
	"brn": true,
	"gry": true,
	"grn": true,
	"hzl": true,
	"oth": true,
}

var pidRegex, _ = regexp.Compile("^[0-9]{9}$")

func getFields(rawPassport *string) map[string]interface{} {
	fields := make(map[string]interface{})
	for _, field := range strings.Fields(*rawPassport) {
		key := field[:3]
		value := field[4:]

		if _, ok := intFields[key]; ok {
			intValue, err := strconv.ParseInt(value, 10, 64)
			if err != nil {
				panic(err)
			}
			fields[key] = int(intValue)
		} else {
			fields[key] = value
		}
	}

	return fields
}

func isValidPartOne(fieldsp *map[string]interface{}) bool {
	fields := *fieldsp
	// Part one logic
	// Valid if:
	//  - All fields
	//  - Missing CID but all other fields
	if len(fields) == MaxFieldCount {
		return true
	} else {
		if len(fields) == MaxFieldCount- 1 {
			if _, ok := fields["cid"]; !ok {
				return true
			}
		}
	}

	return false
}

func isValidPartTwo(fieldsp *map[string]interface{}) bool {
	if !isValidPartOne(fieldsp) {
		return false
	}

	fields := *fieldsp

	byr := fields["byr"].(int)
	if  byr < 1920 || byr > 2002 {
		return false
	}

	iyr := fields["iyr"].(int)
	if iyr < 2010 || iyr > 2020 {
		return false
	}

	eyr := fields["eyr"].(int)
	if eyr < 2020 || eyr > 2030 {
		return false
	}

	hgt := fields["hgt"].(string)
	if strings.HasSuffix(hgt, "cm") {
		hgt = hgt[:len(hgt) - 2]
		intHgt, err := strconv.ParseInt(hgt, 10, 64)
		if err != nil {
			return false
		} else if intHgt < 150 || intHgt > 193 {
			return false
		}
	} else if strings.HasSuffix(hgt, "in") {
		hgt = hgt[:len(hgt) - 2]
		intHgt, err := strconv.ParseInt(hgt, 10, 64)
		if err != nil {
			return false
		} else if intHgt < 59 || intHgt > 76 {
			return false
		}
	} else {
		return false
	}

	hcl := fields["hcl"].(string)
	if !strings.HasPrefix(hcl, "#") || len(hcl) != 7 {
		return false
	}
	hcl = hcl[1:]

	_, err := strconv.ParseUint(hcl, 16, 64)
	if err != nil {
		return false
	}

	ecl := fields["ecl"].(string)
	if _, ok := eyeColours[ecl]; !ok {
		return false
	}

	pid := fields["pid"].(string)

	return pidRegex.MatchString(pid)
}

func main() {
	rawInput := filereader.ReadStringFile("input")

	partOneValid, partTwoValid := 0, 0

	var currentRawPassport string
	for _, line := range rawInput {
		if line == "" {
			currentRawPassport = strings.Trim(currentRawPassport, " ")

			fields := getFields(&currentRawPassport)

			if isValidPartOne(&fields) {
				partOneValid++
			}

			if isValidPartTwo(&fields) {
				partTwoValid++
			}

			currentRawPassport = ""
		} else {
			currentRawPassport += line + " "
		}
	}

	lastFields := getFields(&currentRawPassport)

	if isValidPartOne(&lastFields) {
		partOneValid++
	}

	if isValidPartTwo(&lastFields) {
		partTwoValid++
	}



	fmt.Println("Part One:", partOneValid)
	fmt.Println("Part Two:", partTwoValid)

}