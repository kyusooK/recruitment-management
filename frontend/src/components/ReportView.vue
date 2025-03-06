<template>

    <v-data-table
        :headers="headers"
        :items="report"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReportView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "totalApplyCount", value: "totalApplyCount" },
                { text: "passedCount", value: "passedCount" },
            ],
            report : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/reports'))

            temp.data._embedded.reports.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.report = temp.data._embedded.reports;
        },
        methods: {
        }
    }
</script>

