pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

fn part1(input: &str) {
    let result: u32 = input
        .lines()
        .map(|line| {
            let mut first = None;
            let mut last = None;
            for character in line.chars() {
                let x = character.to_digit(10);
                first = first.or(x);
                last = x.or(last);
            }

            return first.unwrap() * 10 + last.unwrap();
        })
        .sum();

    println!("Part one: {}", result);
}

fn part2(input: &str) {
    let mapping = [
        ("one", 1),
        ("1", 1),
        ("two", 2),
        ("2", 2),
        ("three", 3),
        ("3", 3),
        ("four", 4),
        ("4", 4),
        ("five", 5),
        ("5", 5),
        ("six", 6),
        ("6", 6),
        ("seven", 7),
        ("7", 7),
        ("eight", 8),
        ("8", 8),
        ("nine", 9),
        ("9", 9),
    ];

    let result: u32 = input
        .lines()
        .map(|line| {
            let (_, first) = mapping
                .iter()
                .filter_map(|(expr, val)| line.find(expr).map(|pos| (pos, val)))
                .min_by_key(|&(pos, _)| pos)
                .unwrap();

            let (_, second) = mapping
                .iter()
                .filter_map(|(expr, val)| line.rfind(expr).map(|pos| (pos, val)))
                .max_by_key(|&(pos, _)| pos)
                .unwrap();

            first * 10 + second
        })
        .sum();

    println!("Part two: {}", result);
}
