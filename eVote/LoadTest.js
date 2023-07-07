import http from 'k6/http';
import { check } from 'k6';


export let options={
    stages:[
        {duration:'5m', target:100},//move from 1 to 100 requests over 5 mins
        {duration:'10m', target:100},//stay at 100 requests for 10 mins
        {duration:'5m', target:0},// ramp-down to 0 users
    ]
}

export default function () {
    var url = "http://localhost:5003/api/v1/votingEventsLookup/?id=9a372a69-0693-41a5-bed4-3819e2b07fcf";


    var params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.get(url, params);
    check(res, {
        'Event retrieved with success!': (r) => r.status === 200,
    });
    if (res.status !== 200) {
        console.log(`Request failed. Status: ${res.status}, Body: ${res.body}`);
    }
}
