use std::collections::BTreeMap;
use std::ops::{Bound, Range};

pub fn solve(input: &str) {
    part1(input);
    part2(input);
}

#[derive(Debug)]
struct SeedInformation {
    seeds: Vec<u64>,
    seed_to_soil: BTreeMap<u64, (Range<u64>, Range<u64>)>,
    soil_to_fertilizer: BTreeMap<u64, (Range<u64>, Range<u64>)>,
    fertilizer_to_water: BTreeMap<u64, (Range<u64>, Range<u64>)>,
    water_to_light: BTreeMap<u64, (Range<u64>, Range<u64>)>,
    light_to_temperature: BTreeMap<u64, (Range<u64>, Range<u64>)>,
    temperature_to_humidity: BTreeMap<u64, (Range<u64>, Range<u64>)>,
    humidity_to_location: BTreeMap<u64, (Range<u64>, Range<u64>)>,
}

fn parse(input: &str) -> SeedInformation {
    let (_, remainder) = input.split_once("seeds: ").expect("value");

    let (seeds, _) = remainder.split_once("\n").expect("newline");

    let seed_vec: Vec<u64> = seeds
        .split(" ")
        .map(|seed| seed.parse::<u64>().expect("number"))
        .collect();

    SeedInformation {
        seeds: seed_vec,
        seed_to_soil: parse_heading(input, "seed-to-soil map:\n"),
        soil_to_fertilizer: parse_heading(input, "soil-to-fertilizer map:\n"),
        fertilizer_to_water: parse_heading(input, "fertilizer-to-water map:\n"),
        water_to_light: parse_heading(input, "water-to-light map:\n"),
        light_to_temperature: parse_heading(input, "light-to-temperature map:\n"),
        temperature_to_humidity: parse_heading(input, "temperature-to-humidity map:\n"),
        humidity_to_location: parse_heading(input, "humidity-to-location map:\n"),
    }
}

fn parse_heading(input: &str, heading: &str) -> BTreeMap<u64, (Range<u64>, Range<u64>)> {
    let mut map = BTreeMap::new();

    let (_, data) = input.split_once(heading).expect("valid heading");
    let (data, _) = data.split_once("\n\n").unwrap_or((data, ""));

    data.lines().for_each(|line| {
        let (destination, remainder) = line.split_once(" ").expect("space");
        let (source, range) = remainder.split_once(" ").expect("space");

        let destination: u64 = destination.parse().expect("number");
        let source: u64 = source.parse().expect("number");
        let range: u64 = range.parse().expect("number");

        map.insert(
            source,
            (source..(source + range), destination..(destination + range)),
        );
    });

    map
}

fn get_seed_location(info: &SeedInformation, seed: u64) -> u64 {
    let maps = vec![
        &info.seed_to_soil,
        &info.soil_to_fertilizer,
        &info.fertilizer_to_water,
        &info.water_to_light,
        &info.light_to_temperature,
        &info.temperature_to_humidity,
        &info.humidity_to_location,
    ];

    let mut value = seed;

    for map in maps {
        let upper_bound = map.upper_bound(Bound::Included(&value));

        if upper_bound.value().is_some() {
            let (source_range, destination_range) = upper_bound.value().unwrap();
            if source_range.contains(&value) {
                let diff = source_range.end - value;
                value = destination_range.end - diff;
            }
        }
    }

    value
}

fn part1(input: &str) {
    let info = parse(input);

    let result = info
        .seeds
        .iter()
        .map(|seed| get_seed_location(&info, *seed))
        .min()
        .unwrap();

    println!("Part 1: {result}");
}

fn part2(input: &str) {
    let info = parse(input);

    // nah, i'm too lazy to do it properly, sorry :D
    // the idea would be to make get seed location work
    // on ranges
    let result = info
        .seeds
        .chunks(2)
        .into_iter()
        .flat_map(|chunk| {
            (chunk[0]..chunk[0] + chunk[1])
                .into_iter()
                .map(|seed| get_seed_location(&info, seed))
        })
        .min()
        .unwrap();

    println!("Part 2: {result}");
}
