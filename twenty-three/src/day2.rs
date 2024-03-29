pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

fn parse(input: &str) -> Vec<(u32, u32, u32, u32)> {
    input
        .lines()
        .map(|line| {
            let (game, moves) = line.split_once(": ").expect("colon space");
            let (_, game) = game.split_once(" ").expect("space");
            let game: u32 = game.parse().expect("game id");

            let (r, g, b) = moves
                .trim()
                .split(";")
                .map(|selection| {
                    let mut r = 0;
                    let mut g = 0;
                    let mut b = 0;
                    for game_move in selection.split(",") {
                        let (count, colour) = game_move.trim().split_once(' ').expect("space");
                        let count: u32 = count.parse().expect("number");
                        match colour {
                            "red" => r += count,
                            "green" => g += count,
                            "blue" => b += count,
                            other => unreachable!("colour={other}"),
                        }
                    }
                    (r, g, b)
                })
                .reduce(|(max_r, max_g, max_b), (r, g, b)| {
                    (max_r.max(r), max_g.max(g), max_b.max(b))
                })
                .expect("valid");

            (game, r, g, b)
        })
        .collect::<Vec<_>>()
}

fn part1(input: &str) {
    let result: u32 = parse(input)
        .iter()
        .copied()
        .map(|(game, r, g, b)| {
            if (r > 12) || (g > 13) || (b > 14) {
                return 0;
            }
            game
        })
        .sum();
    println!("Part 1: {}", result);
}

fn part2(input: &str) {
    let result: u32 = parse(input)
        .iter()
        .copied()
        .map(|(_, r, g, b)| r * g * b)
        .sum();
    println!("Part 2: {}", result);
}
