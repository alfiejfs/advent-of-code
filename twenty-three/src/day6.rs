pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

fn parse(input: &str) -> Vec<(u32, u32)> {
    let lines: Vec<&str> = input.lines().collect();

    let times = lines
        .get(0)
        .expect("line")
        .split_whitespace()
        .skip(1)
        .map(|s| s.parse().unwrap());

    let distances = lines
        .get(1)
        .expect("line")
        .split_whitespace()
        .skip(1)
        .map(|l| l.parse().unwrap());

    times.zip(distances).collect()
}

fn parse_two(input: &str) -> (u64, u64) {
    let lines: Vec<&str> = input.lines().collect();

    let time = lines
        .get(0)
        .expect("line")
        .replace("Time:", "")
        .replace(" ", "")
        .parse()
        .unwrap();

    let distance = lines
        .get(1)
        .expect("line")
        .replace("Distance:", "")
        .replace(" ", "")
        .parse()
        .unwrap();

    (time, distance)
}

fn part1(input: &str) {
    let result: u32 = parse(input)
        .iter()
        .map(|(time, distance)| {
            let mut count = 0;

            // v = d/t
            for i in 1..*time {
                if i * (time - i) > *distance {
                    count += 1;
                }
            }
            count
        })
        .filter(|x| *x > 1)
        .fold(1, |a, b| a * b);

    println!("Part 1: {result}");
}

fn part2(input: &str) {
    let (time, distance) = parse_two(input);

    let mut count = 0;

    // v = d/t
    for i in 1..time {
        if i * (time - i) > distance {
            count += 1;
        }
    }

    println!("Part 2: {count}");
}
