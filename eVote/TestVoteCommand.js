import http from 'k6/http';
import { check } from 'k6';

export let options = {
    vus: 1,
    iterations:1,
    duration: '1h',
};

function getRandomOption() {
    return Math.random() < 0.5 ? 'Liria' : 'Capitol';
}

function getRandomAttendance() {
    return Math.random() < 0.8;
}

export function setup() {
    const getInvitesUrl = 'http://localhost:5005/api/v1/invitesLookup/?id=0c2dfbff-9f87-4ebb-a1ec-f43cd14eb7b9';
    let getInvitesResponse = http.get(getInvitesUrl);

    if (getInvitesResponse.status !== 200) {
        console.error("Failed to fetch invites:", getInvitesResponse.status, getInvitesResponse.body);
        return [];
    }

    let invites = JSON.parse(getInvitesResponse.body).invites;
    return invites;
}

export default function (data) {
    const voteUrl = 'http://localhost:5002/api/v1/votingEventCollection/0c2dfbff-9f87-4ebb-a1ec-f43cd14eb7b9/vote';
    for (let invite of data) {
        if (getRandomAttendance()) {
            let id_invite = invite.id;
            let option = getRandomOption();

            let payload = JSON.stringify({
                id_invite: id_invite,
                option: option,
            });

            let params = {
                headers: {
                    'Content-Type': 'application/json',
                }
            };

            let postResponse = http.post(voteUrl, payload, params);
            check(postResponse, {
                'Vote submitted successfully': (resp) => resp.status === 200,
            });

            if (postResponse.status !== 200) {
                console.log(postResponse);
            }
        }
    }
}
