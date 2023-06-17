import http from 'k6/http';
import { sleep } from 'k6';
import { check } from 'k6';

export let options = {
    vus: 1, // Numărul de utilizatori virtuali
    iterations: '1',
};


function getRandomOption() {
    return Math.random() < 0.5 ? 'Lilia' : 'Capitol';
}

export function setup() {
    const getInvitesUrl = 'http://localhost:5005/api/v1/invitesLookup/?id=cbd902c2-e282-4fab-9f38-5278e1826e18';
    let getInvitesResponse = http.get(getInvitesUrl);

    if (getInvitesResponse.status !== 200) {
        console.error("Failed to fetch invites:", getInvitesResponse.status, getInvitesResponse.body);
        return [];
    }



    return JSON.parse(getInvitesResponse.body).invites;
}
export default function (data) {
    const voteUrl = 'http://localhost:5002/api/v1/votingEventCollection/vote/cbd902c2-e282-4fab-9f38-5278e1826e18';

    for (let invite of data) {
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
            'Vote submitted successfully': (resp) => resp.status === 201,
        });
        if(postResponse.status !== 201){
            console.log(postResponse)
        }
        sleep(1);
    }

}

