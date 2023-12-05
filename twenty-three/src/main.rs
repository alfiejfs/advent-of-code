#![feature(btree_cursors)]

use std::fs;
use std::io::{self, Write};

mod day1;
mod day2;
mod day3;
mod day4;
mod day5;

fn main() {
    println!("Enter the day number to run: ");
    print!("> ");
    io::stdout().flush().unwrap();

    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line");
    let input = input.trim();

    if let Ok(day) = input.parse::<u32>() {
        match day {
            1 => solve_day("input/day1.txt", &day1::solve),
            2 => solve_day("input/day2.txt", &day2::solve),
            3 => solve_day("input/day3.txt", &day3::solve),
            4 => solve_day("input/day4.txt", &day4::solve),
            5 => solve_day("input/day5.txt", &day5::solve),
            _ => println!("Day not implemented"),
        }
    }
}

fn solve_day(file_path: &str, solver: &dyn Fn(&str)) {
    if let Ok(input) = fs::read_to_string(file_path) {
        solver(&input);
    } else {
        println!("Failed to read input");
    }
}
