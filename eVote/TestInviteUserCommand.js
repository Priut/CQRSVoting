import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 100,
    iterations: 100,
};

export function setup() {
    // Get all users
    let response = http.get('http://localhost:5001/api/v1/usersLookup/');
    let users = JSON.parse(response.body).users;
    let userIds = users.map(user => user.id);
    return { userIds: userIds };
}

export default function (data) {
    let id_votingEvent = '0c2dfbff-9f87-4ebb-a1ec-f43cd14eb7b9';

    let startIndex = Math.floor(data.userIds.length / options.vus) * (__VU - 1);
    let endIndex = Math.floor(data.userIds.length / options.vus) * __VU;

    for (let i = startIndex; i < endIndex; i++) {
        let id_user = data.userIds[i];
        let payload = JSON.stringify({
            id_user: id_user,
            id_votingEvent: id_votingEvent,
        });

        let params = {
            headers: {
                'Content-Type': 'application/json',
            },
        };


        const res = http.post('http://localhost:5004/api/v1/inviteCollection', payload, params);
        check(res, {
            'status is 201': (r) => r.status === 201,
        });
        if (res.status !== 201) {
            console.log(`Request failed. Status: ${res.status}, Body: ${res.body}`);
        }
    }
}
