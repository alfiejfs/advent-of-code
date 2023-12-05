use std::collections::HashSet;

pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

fn parse(input: &str) -> Vec<(HashSet<u32>, HashSet<u32>)> {
    input
        .lines()
        .map(|line| {
            let (_, numbers) = line.split_once(": ").expect("colon space");
            let (left, right) = numbers.split_once(" | ").expect("space pipe space");

            let left = left.split(" ").filter_map(|x| x.parse().ok()).collect();
            let right = right.split(" ").filter_map(|x| x.parse().ok()).collect();

            (left, right)
        })
        .collect::<Vec<_>>()
}

fn part1(input: &str) {
    let result: u32 = parse(input)
        .iter()
        .map(|(numbers, card)| numbers.intersection(card).collect::<Vec<_>>().len())
        .filter(|&size| size > 0)
        .map(|size| 2u32.pow((size - 1) as u32))
        .sum();

    println!("Part 1: {result}");
}

fn part2(input: &str) {
    // lazily assuming the cards are inserted in order
    let result: Vec<usize> = parse(input)
        .iter()
        .map(|(numbers, card)| numbers.intersection(card).collect::<Vec<_>>().len())
        .collect();

    let mut copy_count: Vec<u32> = vec![1; result.len()];
    for (i, &matches) in result.iter().enumerate() {
        if matches == 0 {
            continue;
        }

        let count = copy_count[i];

        for j in 1..=matches {
            copy_count.get_mut(i + j).map(|x| *x += count);
        }
    }

    let result: u32 = copy_count.iter().sum();

    println!("Part 2: {result}");
}
