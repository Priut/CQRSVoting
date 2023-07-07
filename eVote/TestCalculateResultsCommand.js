import http from 'k6/http';
import { check } from 'k6';

export let options = {
    vus: 1,
    iterations: 1,
};

export default function () {
    let params = {
        headers: {
            'Content-Type': 'application/json',
        }
    };
    const res = http.post('http://localhost:5002/api/v1/votingEventCollection/0c2dfbff-9f87-4ebb-a1ec-f43cd14eb7b9/results', params);
    check(res, {
        'Results calculated succesfully!': (r) => r.status === 200,
    });
    if (res.status !== 200) {
        console.log(`Request failed. Status: ${res.status}, Body: ${res.body}`);
    }

}