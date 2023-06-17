import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    vus: 1,
    iterations: 1,
};

export function setup() {
    // Get all users
    let response = http.get('http://localhost:5001/api/v1/usersLookup/');
    let users = JSON.parse(response.body).users;
    let userIds = users.map(user => user.id);
    return { userIds: userIds };
}

export default function (data) {
    let id_votingEvent = 'cbd902c2-e282-4fab-9f38-5278e1826e18';

    for (let id_user of data.userIds) {
        let payload = JSON.stringify({
            id_user: id_user,
            id_votingEvent: id_votingEvent,
        });

        let params = {
            headers: {
                'Content-Type': 'application/json',
            },
        };

        // Send POST request to invite user to the event
        http.post('http://localhost:5004/api/v1/inviteCollection', payload, params);
    }
    sleep(1);
}