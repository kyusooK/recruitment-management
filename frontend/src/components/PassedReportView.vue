<template>

    <v-data-table
        :headers="headers"
        :items="passedReport"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'PassedReportView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "name", value: "name" },
                { text: "summationScore", value: "summationScore" },
                { text: "interviewScore", value: "interviewScore" },
                { text: "position", value: "position" },
            ],
            passedReport : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/passedReports'))

            temp.data._embedded.passedReports.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.passedReport = temp.data._embedded.passedReports;
        },
        methods: {
        }
    }
</script>

