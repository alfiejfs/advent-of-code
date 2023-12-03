use std::collections::HashMap;

pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

fn part1(input: &str) {
    let lines: Vec<_> = input.lines().collect();
    let mut sum = 0;
    for (line_num, line) in lines.iter().enumerate() {
        let prev_line = if line_num > 0 {
            lines.get(line_num - 1)
        } else {
            None
        };
        let next_line = lines.get(line_num + 1);

        let mut start_index: Option<usize> = None;
        for (i, c) in ((*line).to_string() + ".").chars().enumerate() {
            if c.is_numeric() {
                start_index = start_index.or(Some(i));
            } else {
                if start_index.is_none() {
                    continue;
                }

                let start_index_val = start_index.unwrap();
                let check_start_index = if start_index_val > 0 {
                    start_index_val - 1
                } else {
                    0
                };


                if has_symbol_at_index(check_start_index, Some(line))
                    || has_symbol_at_index(i, Some(line))
                    || has_symbol_over_range(check_start_index, i, prev_line)
                    || has_symbol_over_range(check_start_index, i, next_line)
                {
                    sum += line[start_index_val..i].parse::<u64>().expect("valid int");
                }
                
                start_index = None;
            }
        }
    }

    println!("Part 1: {sum}");
}

fn part2(input: &str) {
    let lines: Vec<_> = input.lines().collect();
    
    let mut gears = HashMap::new();
    for (line_num, line) in lines.iter().enumerate() {
        let prev_line = if line_num > 0 {
            lines.get(line_num - 1)
        } else {
            None
        };
        let next_line = lines.get(line_num + 1);

        let mut start_index: Option<usize> = None;
        for (i, c) in ((*line).to_string() + ".").chars().enumerate() {
            if c.is_numeric() {
                start_index = start_index.or(Some(i));
            } else {
                if start_index.is_none() {
                    continue;
                }

                let start_index_val = start_index.unwrap();
                let check_start_index = if start_index_val > 0 {
                    start_index_val - 1
                } else {
                    0
                };

                let num = line[start_index_val..i].parse::<u64>().expect("valid int");

                [prev_line, Some(line), next_line].iter()
                    .zip([-1, 0, 1])
                    .map(|(l, o)| (get_gears(check_start_index, i, *l), o))
                    .for_each(|(found_gears, offset)| {
                        for gear in found_gears {
                            let key = (gear, (line_num as isize + offset) as usize);
                            if !gears.contains_key(&key) {
                                gears.insert(key, vec![]);
                            }
                            gears.get_mut(&key).expect("vector").push(num);
                        }
                    });
                
                start_index = None;
            }
        }
    }

    let result: u64 = gears.iter()
        .filter(|(_, v)| v.len() == 2)
        .map(|(_, v)| v.iter().fold(1, |acc, &x| acc * x))
        .sum();

    println!("Part 2: {result}");
}

fn has_symbol_over_range(start: usize, end: usize, line: Option<&&str>) -> bool {
    for i in start..=end {
        let c = match line.and_then(|l| l.chars().nth(i)) {
            Some(x) => x,
            None => break,
        };

        if c != '.' && !c.is_numeric() {
            return true;
        }
    }
    return false;
}

fn has_symbol_at_index(index: usize, line: Option<&&str>) -> bool {
    let c = match line.and_then(|l| l.chars().nth(index)) {
        Some(x) => x,
        None => return false,
    };

    c != '.' && !c.is_numeric()
}

fn get_gears(start: usize, end: usize, line: Option<&&str>) -> Vec<usize> {
    let mut gears = vec![];
    for i in start..=end {
        let c = match line.and_then(|l| l.chars().nth(i)) {
            Some(x) => x,
            None => break,
        };

        if c == '*' {
            gears.push(i);
        }
    }
    gears
}