pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

fn part1(input: &str) {
    let mut sum = 0;
    for line in input.lines() {
        let mut first = None;
        let mut last = None;
        for character in line.chars() {
            let x = character.to_digit(10);
            first = first.or(x);
            last = x.or(last);
        }

        if first.is_some() {
            sum += first.unwrap() * 10 + last.unwrap();
        }
    }
    println!("Part one: {}", sum);
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

    let mut sum = 0;
    for line in input.lines() {
        let mut first = None;
        let mut last = None;

        for i in 0..line.len() {
            let x = mapping
                .into_iter()
                .find_map(|(prefix, n)| line[i..].starts_with(prefix).then_some(n));

            first = first.or(x);
            last = x.or(last);
        }

        if first.is_some() {
            sum += first.unwrap() * 10 + last.unwrap();
        }
    }

    println!("Part two: {}", sum);
}
