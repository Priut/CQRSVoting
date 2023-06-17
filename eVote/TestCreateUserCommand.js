import http from 'k6/http';
import { check, sleep } from 'k6';

const firstNames = ["John", "Jane", "Michael", "Emily", "David", "Sara"];
const lastNames = ["Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia"];
const contries = ["Romania", "Spania", "Anglia", "Germania", "Turcia", "Croatia"];
const counties = ["Iasi", "Vaslui", "Suceava", "Dolj", "Constanta", "Bucuresti"];
const cities = ["Iasi", "Vaslui", "Suceava", "Pascani", "Constanta", "Ploiesti"];
const workplaces = ["SCC", "Amazon", "Continental", "Google", "Docker", "Facebook"];
function getRandomItem(arr) {
    return arr[Math.floor(Math.random() * arr.length)];
}

function randomDate(start, end) {
    const date = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();

    return `${day}.${month}.${year}`;
}

function randomPhoneNumber() {
    const prefix = '07';
    const secondDigit = String(Math.floor(Math.random() * 9)); // Digit from 0 to 8
    const areaCode = secondDigit + getRandomItem(['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']);

    let phoneNumber = prefix + areaCode;

    for (let i = 0; i < 6; i++) {
        phoneNumber += getRandomItem(['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']);
    }
    return phoneNumber;
}

export let options = {
    vus: 5,
    duration: '1m',
};

export default function () {
    const firstName = getRandomItem(firstNames);
    const lastName = getRandomItem(lastNames);
    const name = `${firstName} ${lastName}`;
    const email = `${firstName}.${lastName}@yahoo.com`.toLowerCase();
    const phoneNumber = randomPhoneNumber();
    const birthDate = randomDate(new Date(1980, 0, 1), new Date(2000, 11, 31));
    const country = getRandomItem(contries);
    const county = getRandomItem(counties);
    const city = getRandomItem(cities);
    const workPlace = getRandomItem(workplaces);

    const payload = JSON.stringify({
        name,
        email,
        phoneNumber,
        birthDate,
        country,
        county,
        city,
        workPlace,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post('http://localhost:5000/api/v1/userCollection', payload, params);

    check(res, {
        'status is 201': (r) => r.status === 201,
    });
    if (res.status !== 201) {
        console.log(`Request failed. Status: ${res.status}, Body: ${res.body}`);
        console.log(phoneNumber)
    }
    sleep(1);
}
