import http from 'k6/http';
import { check } from 'k6';


export let options={
    stages:[
        {duration:'1m', target:100},
        {duration:'4m', target:50},
        {duration:'4m', target:1000},
        {duration:'8m', target:1000},
        {duration:'2m', target:200},
        {duration:'4m', target:0},
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
